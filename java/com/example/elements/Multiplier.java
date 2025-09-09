package com.example.elements;

import com.example.gameplay.Movement;

public abstract class Multiplier extends PowerUp{
    protected final Movement movement;

    public Multiplier(double x, double y, double speed, Movement movement) {
        setX(x);
        setY(y);
        setPowerUpSpeed(speed);
        this.movement = movement;
    }
}
