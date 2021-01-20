package ru.bloshound.electricalbusbars.model;

import java.io.Serializable;

public abstract class Busbar implements Serializable {

    private String material;
    private int density;
    private int width;
    private int thickness;
    private int length;



    public Busbar(String material, int density, int width, int thickness, int length) {
        this.material = material;
        this.density = density;
        this.width = width;
        this.thickness = thickness;
        this.length = length;
    }

    public Busbar(Busbar busbar){
        this.material = busbar.material;
        this.density = busbar.density;
        this.width = busbar.width;
        this.length = busbar.length;
        this.thickness = busbar.thickness;
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
                ", length=" + length +
                '}';
    }
}
