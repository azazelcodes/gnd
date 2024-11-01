package me.azazeldev.gndfiles.gndmain.types;

import me.azazeldev.gndfiles.Main;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public String name;
    public float x;
    public float y;
    public float width;
    public float height;
    public int color;
    public int radius;
    public List<Node> children;
    public Node parent;
    public Node(String name, float x, float y, float width, float height, int color, int radius) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.radius = radius;
        this.children = new ArrayList<>();
        this.parent = null;
    }

    public void addChild(Node child) {
        child.parent = this;
        child.x += this.x;
        child.y += this.y;
        this.children.add(child);
    }

    public boolean isChild() {
        return (this.parent != null);
    }

    public void replaceProperty(int index, String value) {
        switch (index) {
            case 0:
                this.x = Float.parseFloat(value);
                break;
            case 1:
                this.y = Float.parseFloat(value);
                break;
            case 2:
                this.width = Float.parseFloat(value);
                break;
            case 3:
                this.height = Float.parseFloat(value);
                break;
            case 4:
                this.color = Integer.parseInt(value);
                break;
            case 5:
                this.radius = Integer.parseInt(value);
                break;
            default:
                Main.l.info("Invalid case number.");
                break;
        }
    }

    public static Node parse(String nodeName, String[] properties) {
        float x = Float.parseFloat(properties[0]);
        float y = Float.parseFloat(properties[1]);
        float width = Float.parseFloat(properties[2]);
        float height = Float.parseFloat(properties[3]);
        int color = (int) Long.parseLong(properties[4], 16); // Parse HEX to INT (e.x. FF000000 for black)
        int radius = Integer.decode(properties[5]);
        return new Node(nodeName, x, y, width, height, color, radius);
    }

    public boolean intersects(float x, float y) {
        if (Main.test) {
            Main.l.info("(X|Y) " + x + " | " + y + "; Node v");
            Main.l.info(this.toString());
        }
        return this.x <= x && x <= this.x + this.width && this.y <= y && y <= this.y + this.height;
    }

    public String toString() {
        return "Node{name='" + this.name + '\'' + ", x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", color=" + this.color + ", radius=" + this.radius + ", isChild=" + isChild() + ", children=" + this.children + '}';
    }
}