package ru.bloshound.electricalbusbars;

public abstract class Busbar {

    private String material;
    private int density;
    private int length;
    private int width;
    private int thickness;

    public Busbar(String material, int density, int length, int width, int thickness) {
        this.material = material;
        this.density = density;
        this.length = length;
        this.width = width;
        this.thickness = thickness;
    }

    public String getMaterial() {
        return material;
    }

    public int getDensity() {
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
