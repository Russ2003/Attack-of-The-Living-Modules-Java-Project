package com.example.gameplay;

import com.example.attackofthelivingmodules.GameplayController;
import com.example.attackofthelivingmodules.MainApplication;
import com.example.attackofthelivingmodules.Scores;
import com.example.elements.*;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Movement {
    private final List<Bullet> playerBullets;
    private final List<Bullet> enemyBullets;
    private final List<Enemy> enemyList;
    private final List<PowerUp> powerUpList;
    private final List<Player> playerList;
    private final List<Player> deadPlayerList;
    private final GameplayController controller;
    private boolean movingLeft = false;
    private double mobGroupPos = 20;
    private int multiplier = 1;
    private boolean running = true;

    public Movement(List<Bullet> playerBullets, List<Bullet> enemyBullets, List<Enemy> enemyList, List<PowerUp> powerUpList, List<Player> playerList, List<Player> deadPlayerList, GameplayController controller) {
        this.playerBullets = playerBullets;
        this.enemyBullets = enemyBullets;
        this.enemyList = enemyList;
        this.powerUpList = powerUpList;
        this.playerList = playerList;
        this.controller = controller;
        this.deadPlayerList = deadPlayerList;
    }

    public void movement(long now, double enemySpeed) {
        mobsMovement(now, enemySpeed);
        nonMobMovement(now, enemySpeed);
        powerUpMovement();
        bulletMovement();
    }

    public int getMultiplier() {return multiplier;}

    public void setMultiplier(int multiplier) {this.multiplier = multiplier;}

    private void bulletMovement() {
        Iterator<Bullet> playerBulletIterator = playerBullets.iterator();
        while (playerBulletIterator.hasNext()) {
            Bullet bullet = playerBulletIterator.next();
            bullet.update();
            if (bullet.getY() < 0 - bullet.getHeight()) {
                playerBulletIterator.remove(); // Remove bullet if it goes off-screen
            }
        }

        Iterator<Bullet> enemyBulletIterator = enemyBullets.iterator();
        while (enemyBulletIterator.hasNext()) {
            Bullet bullet = enemyBulletIterator.next();
            bullet.update();
            if (bullet.getY() > 725 || bullet.getX() + bullet.getWidth() < 0 || bullet.getX() > 650) {
                enemyBulletIterator.remove(); // Remove bullet if it goes off-screen
            }
        }
    }

    public void setMobGroupPos(double mobGroupPos) {this.mobGroupPos = mobGroupPos;}

    private void mobsMovement(long now, double enemySpeed) {
        double movement;
        if (mobGroupPos <= 0) movingLeft = false;
        else if (mobGroupPos + 500 >= 650) movingLeft = true;
        if (movingLeft) movement = -enemySpeed;
        else movement = enemySpeed;
        mobGroupPos += movement;
        for (Enemy enemy : enemyList) {
            if (enemy instanceof Mob) {
                enemy.update(movement);
                enemy.revert(now);
            }
        }
    }

    private void nonMobMovement(long now, double enemySpeed) {
        for (Enemy enemy : enemyList) {
            if (!(enemy instanceof Mob)) {
                enemy.update(enemySpeed);
                enemy.revert(now);
            }
        }
    }

    private void powerUpMovement() {
        Iterator<PowerUp> powerUpIterator = powerUpList.iterator();
        while (powerUpIterator.hasNext()) {
            PowerUp powerUp = powerUpIterator.next();
            powerUp.update();
            if (powerUp.getY() > 725 || powerUp.getX() + powerUp.getWidth() < 0 || powerUp.getX() > 650) {
                powerUpIterator.remove(); // Remove bullet if it goes off-screen
            }
        }
    }


    private boolean isColliding(GameObject a, GameObject b) {
        return a.getX() < b.getX() + b.getWidth() && a.getX() + a.getWidth() > b.getX() && a.getY() < b.getY() + b.getHeight() && a.getY() + a.getHeight() > b.getY();
    }

    public void checkCollision(long now, Audio audio) {
        Iterator<Bullet> playerBulletIterator = playerBullets.iterator();
        Player playerForDam;
        if (playerList.isEmpty()) playerForDam = deadPlayerList.getLast();
        else playerForDam = playerList.get(0);
        while (playerBulletIterator.hasNext()) {
            Bullet bullet = playerBulletIterator.next();
            Iterator<Enemy> enemyIterator = enemyList.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                if (isColliding(bullet,enemy)) {
                    playerBulletIterator.remove();
                    enemy.takeDamage(playerForDam.getPlayerBulletDamage(), now);
                    audio.playEnemyHitEffect();
                    if (!enemy.isAlive()) {
                        switch (enemy.getEnemyType()) {
                            case 1 -> controller.setScore(controller.getScore() + 10 * multiplier);
                            case 2 -> controller.setScore(controller.getScore() + 25 * multiplier);
                            case 3 -> controller.setScore(controller.getScore() + 100 * multiplier);
                            case 4 -> controller.setScore(controller.getScore() + 1000 * multiplier);
                            default -> {}
                        }
                        enemyIterator.remove();
                    }
                    break;
                }
            }
        }

        Iterator<Bullet> enemyBulletIterator = enemyBullets.iterator();
        while (enemyBulletIterator.hasNext()) {
            Bullet bullet = enemyBulletIterator.next();
            Iterator<Player> playerIterator = playerList.iterator();
            while (playerIterator.hasNext()) {
                Player player = playerIterator.next();
                if (isColliding(player, bullet)) {
                    enemyBulletIterator.remove();
                    player.takeDamage(1, now);
                    audio.playPlayerHitEffect();
                    if (player.getPlayerNumber() == 1) controller.setLives1(player.getLives());
                    else controller.setLives2(player.getLives());
                    if (!player.isAlive()) {
                        deadPlayerList.add(player);
                        playerIterator.remove();
                        if (playerList.isEmpty()) {
                            audio.stopBackgroundMusic();
                            audio.stopFinalBossMusic();
                            audio.playMainMenuMusic();
                            running = false;
                            MainApplication app = new MainApplication();
                            PauseTransition pause = new PauseTransition(Duration.millis(100));
                            String fileName;
                            String fxmlFile;
                            if (deadPlayerList.size() == 1) fileName = "singlePlayerLeaderboard.txt";
                            else fileName = "twoPlayerLeaderboard.txt";
                            List<Scores> ranking = readFile(fileName);
                            if (ranking.get(4).getScore() >= controller.getScore()) fxmlFile = "Leaderboard.fxml";
                            else fxmlFile = "EnterName.fxml";
                            pause.setOnFinished(e -> {
                                try {
                                    app.changeScene(fxmlFile, controller.getScore(), deadPlayerList.size(), audio);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            });
                            pause.play();
                        }
                        break;
                    }
                }
            }
        }

        Iterator<PowerUp> powerUpIterator = powerUpList.iterator();
        while (powerUpIterator.hasNext()) {
            PowerUp powerUp = powerUpIterator.next();
            for (Player player : playerList) {
                if (isColliding(player, powerUp)) {
                    for (Player players : playerList) {
                        powerUp.effects(players, now, multiplier);
                    }
                    audio.playPowerUpEffect();
                    powerUpIterator.remove();
                    break;
                }
            }
        }
    }

    private List<Scores> readFile(String fileName) {
        List<Scores> ranking = new ArrayList<>();
        File file = new File("target/classes/com/example/attackofthelivingmodules/assets/" + fileName);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] arr = line.split(",");
                ranking.add(new Scores(arr[0], Integer.parseInt(arr[1])));
            }
        } catch (Exception e) {
            System.out.println("Some Error Occurred LOL!");
        }
        return ranking;
    }

    public boolean isRunning() {return running;}
}
