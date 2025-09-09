package com.example.elements;

import com.example.attackofthelivingmodules.GameplayController;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ScreenClear extends PowerUp{
    private final List<Bullet> enemyBullets;
    private final List<Enemy> enemyList;
    private final GameplayController controller;

    public ScreenClear(double x, double y, double speed, List<Enemy> enemyList, List<Bullet> enemyBullets, GameplayController controller) {
        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Sprites/PowerUp_ScreenClear.png"))));
        setX(x);
        setY(y);
        setPowerUpSpeed(speed);
        this.enemyList = enemyList;
        this.enemyBullets = enemyBullets;
        this.controller = controller;
    }

    public void effects(Player player, long now, int multiplier) {
        Iterator<Enemy> enemyIterator = enemyList.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            switch (enemy.getEnemyType()) {
                case 1 -> controller.setScore(controller.getScore() + 10 * multiplier);
                case 2 -> controller.setScore(controller.getScore() + 25 * multiplier);
                case 3 -> controller.setScore(controller.getScore() + 100 * multiplier);
                case 4 -> controller.setScore(controller.getScore() + 1000 * multiplier);
                default -> {
                }
            }
            enemyIterator.remove();
        }

        enemyBullets.clear();
    }
}
