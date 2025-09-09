package com.example.elements;

import javafx.scene.image.Image;

import java.util.Objects;

public class Invincibility extends PowerUp{
    public Invincibility(double x, double y, double speed) {
        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/PowerUp_Invincibility.png"))));
        setX(x);
        setY(y);
        setPowerUpSpeed(speed);
    }

    public void effects(Player player, long now, int multiplier) {
        if (!player.isPowerUpActive()) {
            player.setCannotBeDamaged();
            player.setPowerUpPickUpTime(now);
            player.setPowerUpActive();
        }
    }
}
