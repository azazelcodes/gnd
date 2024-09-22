package me.azazeldev.gndfiles.gndmain.types;

public class Scrollable extends Node {
    public int scrollDir;
    public int scrollProgress;
    public Scrollable(String name, float x, float y, float width, float height, int color, int radius, int scrollDir, int scrollProgress) {
        super(name, x, y, width, height, color, radius);

        this.scrollDir = scrollDir;
        this.scrollProgress = scrollProgress;
    }

    public static Scrollable parse(String nodeName, String[] properties) {
        float x = Float.parseFloat(properties[0]);
        float y = Float.parseFloat(properties[1]);
        float width = Float.parseFloat(properties[2]);
        float height = Float.parseFloat(properties[3]);
        int color = Integer.decode(properties[4]);
        int radius = Integer.decode(properties[5]);
        int scrollDir = Integer.decode(properties[6]);
        return new Scrollable(nodeName, x, y, width, height, color, radius, scrollDir, 0);
    }

    public String toString() {
        return "Scrollable{name='" + this.name + '\'' + ", x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", color=" + this.color + ", radius=" + this.radius + ", scrollDir=" + this.scrollDir+ ", scrollProgress=" + this.scrollProgress + ", isChild=" + isChild() + ", children=" + this.children + '}';
    }
}