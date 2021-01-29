package ru.bloshound.electricalbusbars;

public abstract class Busbar {

    String material;
    double density;
    int length;
    int width;
    int thickness;

    public Busbar(String material, double density, int length, int width, int thickness) {
        this.material = material;
        this.density = density;
        this.length = length;
        this.width = width;
        this.thickness = thickness;
    }

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
