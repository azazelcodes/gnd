package me.azazeldev.gndfiles.gndmain.types;

import me.azazeldev.gndfiles.Main;
import me.azazeldev.gndfiles.gndmain.Parser;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Instance extends Node {
    public Map<String, Node> map;
    public float x;
    public float y;
    public Instance(Map<String, Node> map, float x, float y) {
        super();
        this.map = map;
        this.x = x;
        this.y = y;
    }

    public static Instance parse(String instanceName, String[] properties) {
        Map<String, Node> map;
        try {
            map = Parser.parseFile(new File(Main.mpath + "//" + instanceName + ".gnd"));
            if (Main.test) Main.l.info("Parsed Map: " + map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        float x = Float.parseFloat(properties[0]);
        float y = Float.parseFloat(properties[1]);
        return new Instance(map, x, y);
    }

    public String toString() {
        return "ComplexInstance{complexMap=" + this.map + ", isChild=" + isChild() + ", children=" + this.children + "}";
    }
}