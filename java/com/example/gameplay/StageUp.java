package com.example.gameplay;

import com.example.attackofthelivingmodules.GameplayController;
import com.example.elements.*;

import java.util.List;

public class StageUp {
    private final GameplayController controller;
    private final List<Enemy> enemyList;
    private final List<Bullet> enemyBullets;
    private final Audio audio;
    private int lifeMultiplier = 1;

    public StageUp(GameplayController controller, List<Enemy> enemyList, List<Bullet> enemyBullets, Audio audio) {
        this.controller = controller;
        this.enemyList = enemyList;
        this.enemyBullets = enemyBullets;
        this.audio = audio;
    }

    private double updateStage(double enemySpeed) {
        controller.setStage(controller.getStage() + 1);
        return enemySpeed + (double)controller.getStage() / 50;
    }

    public double initializeEnemies(double initialPos, double enemySpeed, Movement movement) {
        if (enemyList.isEmpty()) {
            movement.setMobGroupPos(initialPos);
            enemySpeed = updateStage(enemySpeed);
            if (controller.getStage() != 1) audio.playNextStageEffect();
            if (controller.getStage() % 10 == 0) lifeMultiplier += 1;
            int currentStage = controller.getStage();
            while (currentStage > 10) currentStage -= 10;
            switch (currentStage) {
                case 1:
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            enemyList.add(new Mob(initialPos + i * 50, initialPos + j * 50, lifeMultiplier));
                        }
                    }
                    audio.stopFinalBossMusic();
                    audio.playBackgroundMusic();
                    break;
                case 2:
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 5; j++) {
                            enemyList.add(new Mob(initialPos + i * 50, initialPos + j * 50, lifeMultiplier));
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            enemyList.add(new Mid(initialPos + i * 75, initialPos + j * 75, 3 * lifeMultiplier));
                        }
                    }
                    break;
                case 4:
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            enemyList.add(new Mob(initialPos + i * 50, initialPos + j * 50, lifeMultiplier));
                        }
                    }
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            enemyList.add(new Mid(initialPos + i * 75, initialPos + j * 75, 3 * lifeMultiplier));
                        }
                    }
                    break;
                case 5:
                    enemyList.add(new Boss(20, 65, 10 * lifeMultiplier, enemyBullets, audio));
                    break;
                case 6:
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            enemyList.add(new Mid(initialPos + i * 75, initialPos + j * 75, 3 * lifeMultiplier));
                        }
                    }
                    break;
                case 7:
                    enemyList.add(new Boss(20, 65, 10 * lifeMultiplier, enemyBullets, audio));
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            enemyList.add(new Mob(initialPos + i * 50, initialPos + j * 50, lifeMultiplier));
                        }
                    }
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            enemyList.add(new Mid(initialPos + i * 75, initialPos + j * 75, 3 * lifeMultiplier));
                        }
                    }
                    break;
                case 8:
                    enemyList.add(new Boss(20, 65, 10 * lifeMultiplier, enemyBullets, audio));
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            enemyList.add(new Mid(initialPos + i * 75, initialPos + j * 75, 3 * lifeMultiplier));
                        }
                    }
                    break;
                case 9:
                    for (int i = 0; i < 2; i++) enemyList.add(new Boss(20 + i * 300, 50 + i * 175, 10 * lifeMultiplier, enemyBullets, audio));
                    break;
                default:
                    enemyList.add(new Nail((double) (625 - 66) / 2, 20, 50 * lifeMultiplier, enemyBullets, controller, audio));
                    audio.stopBackgroundMusic();
                    audio.playFinalBossMusic();
                    break;
            }
        }
        return enemySpeed;
    }
}
