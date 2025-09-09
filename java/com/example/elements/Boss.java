package com.example.elements;

import com.example.gameplay.Audio;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Objects;

public class Boss extends HighTierEnemy{
    private boolean movingLeft = true;

    public Boss(double x, double y, int lives, List<Bullet> enemyBullets, Audio audio) {
        this.normal = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Enemy_Boss.png")));
        this.damage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Enemy_Boss_Damage.png")));
        this.audio = audio;
        setImage(normal);
        setX(x);
        setY(y);
        setLives(lives);
        setEnemyType(3);
        this.enemyBullets = enemyBullets;
        OVERALL_COOLDOWN = 2_500_000_000L;
        CHAIN_COOLDOWN = 750_000_000;
    }

    public void update(double enemySpeed) {
        if (getX() <= 0) {
            movingLeft = false;
            setX(0);
        } else if (getX() + getWidth() >= 650) {
            movingLeft = true;
            setX(650 - getWidth());
        }
        if (movingLeft) enemySpeed = -enemySpeed;
        setX(getX() + enemySpeed);
    }

    public Image getBulletImage() {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/Bullet_Boss.png")));
    }

    public void shoot(long now, double enemySpeed) {
        if (now - lastChainShot >= OVERALL_COOLDOWN) {
            if (now - lastShot >= CHAIN_COOLDOWN && shotNumber < 3) {
                enemyBullets.add(new Bullet(getBulletImage(), getX() + getWidth() / 2 - getBulletImage().getWidth() / 2, getY() + getHeight(), enemySpeed, 0));
                lastShot = now;
                shotNumber += 1;
                audio.playBossBulletEffect();
            } else if (shotNumber >= 3){
                lastChainShot = now;
                shotNumber = 0;
            }
        }
    }
}
