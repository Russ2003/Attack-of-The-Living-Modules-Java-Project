package com.example.elements;

import com.example.gameplay.Movement;
import javafx.scene.image.Image;

import java.util.Objects;

public class Multiplier2x extends Multiplier{
    public Multiplier2x(double x, double y, double speed, Movement movement) {
        super(x, y, speed, movement);
        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/PowerUp_Multiplier2.png"))));
    }

    public void effects(Player player, long now, int multiplier) {
        if (!player.isPowerUpActive()) {
            movement.setMultiplier(movement.getMultiplier() * 2);
            player.setPowerUpPickUpTime(now);
            player.setPowerUpActive();
        }
    }
}
