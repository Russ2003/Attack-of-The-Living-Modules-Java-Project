package com.example.elements;

import javafx.scene.image.Image;

import java.util.Objects;
import java.util.Random;

public class Mid extends Enemy{
    private double targetX;
    private double targetY;
    private boolean targetToUp;
    private boolean targetToRight;
    private boolean targetReachedX = true;
    private boolean targetReachedY = true;

    public Mid(double x, double y, int lives) {
        this.normal = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Enemy_Mid.png")));
        this.damage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Enemy_Mid_Damage.png")));
        setImage(normal);
        setX(x);
        setY(y);
        setLives(lives);
        setEnemyType(2);
    }

    private void getTargets() {
        Random random = new Random();
        targetX = random.nextInt(650 - (int)getWidth());
        targetY = random.nextInt(300) + 10;
        targetReachedX = false;
        targetReachedY = false;
        targetToRight = targetX >= getX();
        targetToUp = targetY >= getY();
    }

    private void moveX(double xMovement) {
        if (!targetReachedX) setX(getX() + xMovement);
        if (targetToRight && getX() >= targetX) targetReachedX = true;
        else if (!targetToRight && getX() <= targetX) targetReachedX = true;
    }

    private void moveY(double yMovement) {
        if (!targetReachedY) setY(getY() + yMovement);
        if (targetToUp && getY() >= targetY) targetReachedY = true;
        else if (!targetToUp && getY() <= targetY) targetReachedY = true;
    }

    public void update(double enemySpeed) {
        if (targetReachedX && targetReachedY) getTargets();
        double yMovement = 2 * enemySpeed;
        double xMovement = 2 * enemySpeed;
        if (!targetToUp) yMovement = -yMovement;
        if (!targetToRight) xMovement = -xMovement;
        moveX(xMovement);
        moveY(yMovement);
    }

    public Image getBulletImage() {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Bullet_Mid.png")));
    }
}
