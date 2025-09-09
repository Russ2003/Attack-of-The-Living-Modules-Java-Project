package com.example.gameplay;

import com.example.attackofthelivingmodules.GameplayController;
import com.example.elements.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.*;


public class SinglePlayerGameplay {

    // Background and Images Used
    private final GraphicsContext gc;
    private final GameplayController controller;
    private final Image backgroundImage= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/EnvironmentAssets/Background_game.png")));
    private final Image player1Left = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Player_1_Left.png")));
    private final Image player1Right = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Player_1_Right.png")));
    private final Image player1LeftDamage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Player_1_Left_Damage.png")));
    private final Image player1RightDamage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Player_1_Right_Damage.png")));
    private final Image playerBullet = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Bullet_Player.png")));

    // Classes for Functions
    private final StageUp stageUp;
    private final Movement movement;
    private final Audio audio;
    private final Random random = new Random();

    // Characters and Bullets in Game
    private final Player player1;
    private final Set<KeyCode> keysPressed = new HashSet<>();
    private final List<Bullet> playerBullets = new ArrayList<>();
    private final List<Bullet> enemyBullets = new ArrayList<>();
    private final List<Enemy> enemyList = new ArrayList<>();
    private final List<PowerUp> powerUpList = new ArrayList<>();
    private final List<Player> playerList = new ArrayList<>();
    private final List<Player> deadPlayerList = new ArrayList<>();

    // Values used in Game;
    private static final long COOLDOWN = 750_000_000; // In nanoseconds
    private static final long enemyBulletCooldown = 3_000_000_000L; // In nanoseconds
    private long lastPlayerBulletTime = 0;
    private long lastEnemyBulletTime;
    private double enemySpeed = 1;
    private static final long SPAWN_TIME = 20_000_000_000L;
    private long lastPowerUpSpawn;
    private static final double powerUpSpeed = 1;

    public SinglePlayerGameplay(Scene scene, GameplayController controller, Audio audio) {
        this.controller = controller;
        movement = new Movement(playerBullets, enemyBullets, enemyList, powerUpList, playerList, deadPlayerList, controller);
        player1 = new Player(player1Left, player1LeftDamage, 325, 525, 3, 1, movement);
        playerList.add(player1);
        controller.setLives1(player1.getLives());
        gc = controller.getCanvas().getGraphicsContext2D();
        scene.setOnKeyPressed(this::handleKeyPress);
        scene.setOnKeyReleased(this::handleKeyRelease);
        gc.drawImage(backgroundImage, 0, 0, 650, 725);
        this.audio = audio;
        audio.stopBackgroundMusic();
        audio.playBackgroundMusic();
        lastEnemyBulletTime = System.nanoTime();
        lastPowerUpSpawn = System.nanoTime();
        stageUp = new StageUp(controller, enemyList, enemyBullets, audio);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (movement.isRunning()) {
                    update(now);
                    render(gc);
                }
            }
        }.start();
    }

    private void update(long now) {
        movement.checkCollision(now, audio);
        enemySpeed = stageUp.initializeEnemies(20, enemySpeed, movement);
        playerUpdates(now);
        shootBullet(now);
        spawnPowerUp(now);
        movement.movement(now, enemySpeed);
    }

    private void render(GraphicsContext gc) {
        gc.clearRect(0, 0, 650, 725);
        gc.drawImage(backgroundImage, 0, 0, 650, 725);
        drawPlayers();
        drawBullets();
        drawEnemies();
        drawPowerUp();
    }



    private void handleKeyPress(KeyEvent event) {keysPressed.add(event.getCode());}

    private void handleKeyRelease(KeyEvent event) {keysPressed.remove(event.getCode());}

    private void drawPlayers() {
        for (Player player : playerList) {
            gc.drawImage(player.getImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight());
        }

        for (Player player : deadPlayerList) {
            gc.drawImage(player.getImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight());
        }
    }

    private void drawEnemies() {
        for (Enemy enemy : enemyList) {
            gc.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
        }
    }

    private void drawBullets() {
        for (Bullet bullet : playerBullets) {
            gc.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
        }

        for (Bullet bullet : enemyBullets) {
            gc.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
        }
    }

    private void drawPowerUp() {
        for (PowerUp powerUp : powerUpList) {
            gc.drawImage(powerUp.getImage(), powerUp.getX(), powerUp.getY(), powerUp.getWidth(), powerUp.getHeight());
        }
    }

    private void shootBullet(long now) {
        if ((keysPressed.contains(KeyCode.SPACE) || keysPressed.contains(KeyCode.ENTER)) && now - lastPlayerBulletTime >= COOLDOWN && player1.isAlive()) {
            playerBullets.add(new Bullet(playerBullet,player1.getX() + player1.getWidth() / 2 - playerBullet.getWidth() / 2 , player1.getY(), -player1.getPlayerSpeed(), 0));
            lastPlayerBulletTime = now;
            audio.playPlayerBulletEffect();
        }

        if (now - lastEnemyBulletTime >= enemyBulletCooldown && !enemyList.isEmpty()) {
            Enemy enemy = enemyList.get(random.nextInt(enemyList.size()));
            if (!(enemy instanceof Nail) && !(enemy instanceof Boss)) {
                enemyBullets.add(new Bullet(enemy.getBulletImage(), enemy.getX() + enemy.getWidth() / 2 - enemy.getBulletImage().getWidth() / 2, enemy.getY() + enemy.getHeight(), enemySpeed, 0));
                lastEnemyBulletTime = now;
                if (enemy instanceof Mob) audio.playMobBulletEffect();
                else if (enemy instanceof Mid) audio.playMidBulletEffect();
            }
        }

        if (enemyList.stream().anyMatch(enemy -> enemy instanceof Boss) || enemyList.stream().anyMatch(enemy -> enemy instanceof Nail)) {
            for (Enemy enemy : enemyList) {
                if (enemy instanceof Boss) {
                    ((Boss) enemy).shoot(now, enemySpeed);
                } else if (enemy instanceof Nail) {
                    ((Nail) enemy).shoot(now, enemySpeed);
                }
            }
        }
    }

    private void spawnPowerUp(long now) {
        if (now - lastPowerUpSpawn >= SPAWN_TIME) {
            int limit;
            if (controller.getStage() % 10 == 0) limit = 6;
            else limit = 7;
            switch (random.nextInt(limit)+1) {
                case 1:
                    powerUpList.add(new SpeedBoost(random.nextDouble(500) + 50, 10, powerUpSpeed));
                    break;
                case 2:
                    powerUpList.add(new Invincibility(random.nextDouble(500) + 50, 10, powerUpSpeed));
                    break;
                case 3:
                    powerUpList.add(new ExtraLife(random.nextDouble(500) + 50, 10, powerUpSpeed, controller));
                    break;
                case 4:
                    powerUpList.add(new Multiplier2x(random.nextDouble(500) + 50, 10, powerUpSpeed, movement));
                    break;
                case 5:
                    powerUpList.add(new Multiplier4x(random.nextDouble(500) + 50, 10, powerUpSpeed, movement));
                    break;
                case 6:
                    powerUpList.add(new AttackUp(random.nextDouble(500) + 50, 10, powerUpSpeed));
                    break;
                case 7:
                    powerUpList.add(new ScreenClear(random.nextDouble(500) + 50, 10, powerUpSpeed, enemyList, enemyBullets, controller));
                    break;
                default:
                    break;
            }
            lastPowerUpSpawn = now;
        }
    }

    private void playerUpdates(long now) {
        for (Player player : playerList) player.revert(now, audio);
        playerMovement();
    }

    private void playerMovement() {
        // Left-Right Movement
        if (keysPressed.contains(KeyCode.LEFT) || keysPressed.contains(KeyCode.A)) {
            player1.setX(player1.getX() - player1.getPlayerSpeed());
            if (player1.isDamaged()) player1.setImage(player1LeftDamage);
            else player1.setImage(player1Left);
        } else if (keysPressed.contains(KeyCode.RIGHT) || keysPressed.contains(KeyCode.D)) {
            player1.setX(player1.getX() + player1.getPlayerSpeed());
            if (player1.isDamaged()) player1.setImage(player1RightDamage);
            else player1.setImage(player1Right);
        }

        // Up-Down Movement
        if (keysPressed.contains(KeyCode.UP) || keysPressed.contains(KeyCode.W)) {
            player1.setY(player1.getY() - player1.getPlayerSpeed());
        } else if (keysPressed.contains(KeyCode.DOWN) || keysPressed.contains(KeyCode.S)) {
            player1.setY(player1.getY() + player1.getPlayerSpeed());
        }

        // Prevent player from moving out of bounds horizontally;
        if (player1.getX() < 0) {
            player1.setX(0);
        } else if (player1.getX() + player1.getWidth() > 650) {
            player1.setX(650 - player1.getWidth());
        }

        // Prevent player from moving out of bounds vertically;
        if (player1.getY() < 525) {
            player1.setY(525);
        } else if (player1.getY() + player1.getHeight() > 725) {
            player1.setY(725 - player1.getHeight());
        }
    }
}
