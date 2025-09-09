package com.example.elements;

import javafx.scene.image.Image;

public abstract class Characters extends GameObject {
    protected int lives;
    protected Image normal;
    protected Image damage;
    protected static final long DAMAGE_DURATION = 100_000_000;
    protected long lastDamageInstance = 0;

    public void setLives(int lives) {this.lives = lives;}

    public int getLives() {return lives;}

    public void takeDamage(int bulletDamage, long now) {
        lives -= bulletDamage;
        setImage(damage);
        lastDamageInstance = now;
    }

    public void revert(long now) {
        if (now - lastDamageInstance >= DAMAGE_DURATION) {
            setImage(normal);
        }
    }
    public boolean isAlive() {
        return lives > 0;
    }
}
