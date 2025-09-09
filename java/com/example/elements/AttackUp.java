package com.example.elements;

import javafx.scene.image.Image;

import java.util.Objects;

public class AttackUp extends PowerUp{
    public AttackUp(double x, double y, double speed) {
        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/PowerUp_AttackUp.png"))));
        setX(x);
        setY(y);
        setPowerUpSpeed(speed);
    }

    public void effects(Player player, long now, int multiplier) {
        player.setPlayerBulletDamage(player.getPlayerBulletDamage() * 2);
    }
}
