package ru.bloshound.electricalbusbars.model;

public class CopperBusbar extends Busbar {

    public CopperBusbar(int width, int thickness, int length) {
        super("Copper", 8930, width, thickness, length);
    }

    public CopperBusbar(Busbar busbar){
        super(busbar);
    }
}
