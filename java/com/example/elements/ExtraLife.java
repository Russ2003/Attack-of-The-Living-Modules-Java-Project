package com.example.elements;

import com.example.attackofthelivingmodules.GameplayController;
import javafx.scene.image.Image;

import java.util.Objects;

public class ExtraLife extends PowerUp{
    private final GameplayController controller;

    public ExtraLife(double x, double y, double speed, GameplayController controller) {
        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/PowerUp_ExtraLife.png"))));
        setX(x);
        setY(y);
        setPowerUpSpeed(speed);
        this.controller = controller;
    }

    public void effects(Player player, long now, int multiplier) {
        if (player.isAlive()) {
            player.setLives(player.getLives() + 1);
            if (player.getPlayerNumber() == 1) controller.setLives1(player.getLives());
            else controller.setLives2(player.getLives());
        }
    }
}
