package com.example.elements;

import javafx.scene.image.Image;

import java.util.Objects;

public class SpeedBoost extends PowerUp{
    public SpeedBoost(double x, double y, double speed) {
        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/PowerUp_SpeedBoost.png"))));
        setX(x);
        setY(y);
        setPowerUpSpeed(speed);
    }

    public void effects(Player player, long now, int multiplier) {
        if (!player.isPowerUpActive()) {
            player.setPlayerSpeed(player.getPlayerSpeed() * 2);
            player.setPowerUpPickUpTime(now);
            player.setPowerUpActive();
        }
    }
}
