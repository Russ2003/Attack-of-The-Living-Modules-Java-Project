package com.example.elements;

import com.example.gameplay.Audio;

import java.util.List;

public abstract class HighTierEnemy extends Enemy{
    protected List<Bullet> enemyBullets;
    protected long OVERALL_COOLDOWN;
    protected long CHAIN_COOLDOWN;
    protected long lastChainShot = System.nanoTime();
    protected long lastShot = 0;
    protected int shotNumber = 0;
    protected Audio audio;

    public abstract void shoot(long now, double enemySpeed);
}
