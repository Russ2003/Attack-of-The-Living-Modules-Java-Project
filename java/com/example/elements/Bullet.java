package com.example.elements;

import javafx.scene.image.Image;

public class Bullet extends GameObject {
    private double xSpeed;
    private double ySpeed;

    public void setXSpeed(double speed) {xSpeed = speed;}

    public void setYSpeed(double speed) {ySpeed = speed;}

    public void update() {
        setY(getY() + ySpeed);
        setX(getX() + xSpeed);
    }

    public Bullet(Image sprite, double x, double y, double ySpeed, double xSpeed) {
        setImage(sprite);
        setX(x);
        setY(y);
        setXSpeed(xSpeed);
        setYSpeed(ySpeed);
    }
}
