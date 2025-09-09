package com.example.elements;

public abstract class PowerUp extends GameObject{
    private double powerUpSpeed;

    public abstract void effects(Player player, long now, int multiplier);

    public void update() {
        setY(getY() + powerUpSpeed);
    }

    public void setPowerUpSpeed(double powerUpSpeed) {this.powerUpSpeed = powerUpSpeed;}
}
