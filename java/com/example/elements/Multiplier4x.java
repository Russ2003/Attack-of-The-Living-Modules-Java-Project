package com.example.elements;

import com.example.gameplay.Movement;
import javafx.scene.image.Image;

import java.util.Objects;

public class Multiplier4x extends Multiplier{
    public Multiplier4x(double x, double y, double speed, Movement movement) {
        super(x, y, speed, movement);
        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/PowerUp_Multiplier4.png"))));
    }

    public void effects(Player player, long now, int multiplier) {
        if (!player.isPowerUpActive()) {
            movement.setMultiplier(movement.getMultiplier() * 4);
            player.setPowerUpPickUpTime(now);
            player.setPowerUpActive();
        }
    }
}
