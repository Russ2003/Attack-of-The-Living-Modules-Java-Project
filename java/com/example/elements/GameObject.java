package com.example.elements;

import javafx.scene.image.Image;

public abstract class GameObject {
    private Image sprite;
    protected double positionX;
    protected double positionY;


    public void setImage(Image image) {sprite = image;}

    public Image getImage() {return sprite;}

    public void setX(double x) {positionX = x;}

    public double getX() {return positionX;}

    public void setY(double y) {positionY = y;}

    public double getY() {return positionY;}

    public double getWidth() {return sprite.getWidth();}

    public double getHeight() {return sprite.getHeight();}

}
