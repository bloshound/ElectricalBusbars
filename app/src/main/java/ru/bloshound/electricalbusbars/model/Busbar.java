package ru.bloshound.electricalbusbars.model;

import java.io.Serializable;

public abstract class Busbar implements Serializable {

    private String material;
    private int density;

    private int width;
    private int thickness;
    private final int section;

    private int length;

    public Busbar(String material, int density, int width, int thickness, int length) {
        this.material = material;
        this.density = density;
        this.width = width;
        this.thickness = thickness;
        this.section = thickness * width;
        this.length = length;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getMaterial() {
        return material;
    }

    public int getDensity() {
        return density;
    }

    public int getWidth() {
        return width;
    }

    public int getThickness() {
        return thickness;
    }

    public int getSection() {
        return section;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Busbar{" +
                "material='" + material + '\'' +
                ", density=" + density +
                ", width=" + width +
                ", thickness=" + thickness +
                ", section=" + section +
                ", length=" + length +
                '}';
    }
}
