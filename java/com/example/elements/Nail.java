package com.example.elements;

import com.example.attackofthelivingmodules.GameplayController;
import com.example.gameplay.Audio;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Nail extends HighTierEnemy{

    private double targetX;
    private boolean targetToRight;
    private boolean targetReachedX = true;
    private int bulletSelection = 0;
    private final Random random = new Random();
    private final GameplayController controller;

    public Nail(double x, double y, int lives, List<Bullet> enemyBullets, GameplayController controller, Audio audio) {
        this.normal = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Enemy_Nail.png")));
        this.damage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Enemy_Nail_Damage.png")));
        this.audio = audio;
        setImage(normal);
        setX(x);
        setY(y);
        setLives(lives);
        setEnemyType(4);
        this.controller = controller;
        this.enemyBullets = enemyBullets;
        OVERALL_COOLDOWN = 7_500_000_000L;
        CHAIN_COOLDOWN = 1_000_000_000;
    }

    public Image getBulletImage() {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Bullet_Nail.png")));
    }

    private void getTarget() {
        Random random = new Random();
        targetX = random.nextInt(650 - (int)getWidth());
        targetReachedX = false;
        targetToRight = targetX >= getX();
    }

    private void moveX(double xMovement) {
        if (!targetReachedX) setX(getX() + xMovement);
        if (targetToRight && getX() >= targetX) targetReachedX = true;
        else if (!targetToRight && getX() <= targetX) targetReachedX = true;
    }


    public void update(double enemySpeed) {
        if (targetReachedX) getTarget();
        if (!targetToRight) enemySpeed = -enemySpeed;
        moveX(enemySpeed);
    }

    public void shoot(long now, double enemySpeed) {
        if (now - lastChainShot >= OVERALL_COOLDOWN) {
            if (bulletSelection == 0) {
                bulletSelection = random.nextInt(4) + 1;
            }
            switch (bulletSelection) {
                case 1 -> bulletRain(now, enemySpeed);
                case 2 -> bulletCenterScatter(now, enemySpeed);
                case 3 -> bulletLeftScatter(now, enemySpeed);
                case 4 -> bulletRightScatter(now, enemySpeed);
                default -> {}
            }
            audio.playNailBulletEffect();
        }
    }

    private void bulletRain(long now, double enemySpeed) {
        if (now - lastShot >= CHAIN_COOLDOWN && shotNumber < 5) {
            for (int i = 0; i < 5; i++) enemyBullets.add(new Bullet(getBulletImage(), 25 + (getBulletImage().getWidth() + 50) * 1.5 * i, getY() + getHeight() / 2, enemySpeed, 0));
            lastShot = now;
            shotNumber += 1;
        } else if (shotNumber >= 5){
            lastChainShot = now;
            shotNumber = 0;
            bulletSelection = 0;
        }
    }

    private void bulletCenterScatter(long now, double enemySpeed) {
        if (now - lastShot >= CHAIN_COOLDOWN && shotNumber < 5) {
            for (int i = -4; i < 5; i++) enemyBullets.add(new Bullet(getBulletImage(), (controller.getCanvas().getWidth() - getBulletImage().getWidth()) / 2, getY() + getHeight() / 2, enemySpeed, enemySpeed * i / 2));
            lastShot = now;
            shotNumber += 1;
        } else if (shotNumber >= 5){
            lastChainShot = now;
            shotNumber = 0;
            bulletSelection = 0;
        }
    }

    private void bulletLeftScatter(long now, double enemySpeed) {
        if (now - lastShot >= CHAIN_COOLDOWN && shotNumber < 5) {
            for (int i = 0; i < 8; i++) enemyBullets.add(new Bullet(getBulletImage(), 25, getY() + getHeight() / 2, enemySpeed, enemySpeed * i / 2));
            lastShot = now;
            shotNumber += 1;
        } else if (shotNumber >= 5){
            lastChainShot = now;
            shotNumber = 0;
            bulletSelection = 0;
        }
    }

    private void bulletRightScatter(long now, double enemySpeed) {
        if (now - lastShot >= CHAIN_COOLDOWN && shotNumber < 5) {
            for (int i = -7; i < 1; i++) enemyBullets.add(new Bullet(getBulletImage(), 625 - getBulletImage().getWidth(), getY() + getHeight() / 2, enemySpeed, enemySpeed * i / 2));
            lastShot = now;
            shotNumber += 1;
        } else if (shotNumber >= 5){
            lastChainShot = now;
            shotNumber = 0;
            bulletSelection = 0;
        }
    }


}
