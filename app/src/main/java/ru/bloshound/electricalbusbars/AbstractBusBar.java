package ru.bloshound.electricalbusbars;

public abstract class AbstractBusBar {

    String material;
    double density;
    int length;
    int width;
    int thickness;

    public String getMaterial() {
        return material;
    }

    public double getDensity() {
        return density;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getThickness() {
        return thickness;
    }
}
