package com.example.elements;

import javafx.scene.image.Image;

import java.util.Objects;

public class Mob extends Enemy {
    public Mob(double x, double y, int lives) {
        this.normal = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Enemy_Mob.png")));
        this.damage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Enemy_Mob_Damage.png")));
        setImage(normal);
        setX(x);
        setY(y);
        setLives(lives);
        setEnemyType(1);
    }

    public void update(double enemySpeed) {
        setX(getX() + enemySpeed);
    }

    public Image getBulletImage() {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Bullet_Mob.png")));
    }
}
