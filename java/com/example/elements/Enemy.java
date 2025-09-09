package com.example.elements;

import javafx.scene.image.Image;

public abstract class Enemy extends Characters{
    private int enemyType;

    public void setEnemyType(int enemyType) {this.enemyType = enemyType;};

    public int getEnemyType() {return enemyType;}

    public abstract void update(double enemySpeed);

    public abstract Image getBulletImage();
}
