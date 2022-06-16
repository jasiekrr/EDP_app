package com.example.edpapp.specials;

import java.util.Map;

public class MapCoordinator {
    private double x;
    private double y;
    private double leftBorder;
    private double rightBorder;
    private double topBorder;
    private double bottomBorder;
    private double sizeX;
    private double sizeY;

    public MapCoordinator(double x, double y){
        this.x = x;
        this.y = y;
        this.sizeX = 254;
        this.sizeY = 254;

        this.leftBorder = -6.0;
        this.topBorder = 54.0;
        this.bottomBorder = 24.0;
        this.rightBorder = 37.0;
    }

    public double calculateX(){
        double degreesPerPixelX = Math.abs(rightBorder - leftBorder) / sizeX;
        return leftBorder + x * degreesPerPixelX;

    }
    public double calculateY() {
        double degreesPerPixelY = Math.abs(topBorder - bottomBorder) / sizeY;
        return topBorder - y * degreesPerPixelY;
    }



    public double getSizeX() {
        return sizeX;
    }

    public void setSizeX(double sizeX) {
        this.sizeX = sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }

    public void setSizeY(double sizeY) {
        this.sizeY = sizeY;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getLeftBorder() {
        return leftBorder;
    }

    public void setLeftBorder(double leftBorder) {
        this.leftBorder = leftBorder;
    }

    public double getRightBorder() {
        return rightBorder;
    }

    public void setRightBorder(double rightBorder) {
        this.rightBorder = rightBorder;
    }

    public double getTopBorder() {
        return topBorder;
    }

    public void setTopBorder(double topBorder) {
        this.topBorder = topBorder;
    }

    public double getBottomBorder() {
        return bottomBorder;
    }

    public void setBottomBorder(double bottomBorder) {
        this.bottomBorder = bottomBorder;
    }
}
