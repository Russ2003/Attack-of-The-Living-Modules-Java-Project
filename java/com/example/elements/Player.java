package com.example.elements;

import com.example.gameplay.Audio;
import com.example.gameplay.Movement;
import javafx.scene.image.Image;

public class Player extends Characters {
    private int playerNumber;
    private int playerBulletDamage = 1;
    private boolean alive = true;
    private boolean isDamaged = false;
    private double playerSpeed = 4;
    private boolean powerUpActive = false;
    private long powerUpPickUpTime = 0;
    private static final long POWER_UP_DURATION = 10_000_000_000L;
    private boolean canBeDamaged = true;
    private Movement movement;

    public Player(Image sprite, Image damaged, int x, int y, int lives, int playerNumber, Movement movement) {
        setImage(sprite);
        normal = sprite;
        damage = damaged;
        setX(x);
        setY(y);
        setLives(lives);
        setPlayerNumber(playerNumber);
        this.movement = movement;
    }

    public void setPlayerNumber(int playerNumber) {this.playerNumber = playerNumber;}

    public int getPlayerNumber() {return playerNumber;}

    @Override
    public void setX(double x) {if (alive) positionX = x;}

    @Override
    public void setY(double y) {if (alive) positionY = y;}

    @Override
    public boolean isAlive() {
        if (!(lives > 0)) alive = false;
        return lives > 0;
    }

    @Override
    public void takeDamage(int bulletDamage, long now) {
        if (canBeDamaged) {
            lives -= bulletDamage;
            setImage(damage);
            isDamaged = true;
            lastDamageInstance = now;
        }
    }

    public boolean isDamaged() {return isDamaged;}

    public void revert(long now, Audio audio) {
        if (now - lastDamageInstance >= DAMAGE_DURATION && alive) {
            isDamaged = false;
            setImage(normal);
        }

        if (now - powerUpPickUpTime >= POWER_UP_DURATION) {
            this.playerSpeed = 3;
            powerUpActive = false;
            canBeDamaged = true;
            movement.setMultiplier(1);
            audio.playPowerDownEffect();
        }
    }

    public double getPlayerSpeed() {return playerSpeed;}

    public void setPlayerSpeed(double playerSpeed) {this.playerSpeed = playerSpeed;}

    public void setPowerUpActive() {powerUpActive = true;}

    public boolean isPowerUpActive() {return powerUpActive;}

    public void setPowerUpPickUpTime(long now) {powerUpPickUpTime = now;}

    public void setCannotBeDamaged() {canBeDamaged = false;}

    public void setPlayerBulletDamage(int playerBulletDamage) {this.playerBulletDamage = playerBulletDamage;}

    public int getPlayerBulletDamage() {return playerBulletDamage;}
}
