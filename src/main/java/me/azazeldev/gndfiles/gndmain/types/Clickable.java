package me.azazeldev.gndfiles.gndmain.types;

import java.util.*;
import java.util.function.Consumer;

public class Clickable extends Node {
    public String methodName;
    public List<String> methodArgs;
    public Clickable(String name, float x, float y, float width, float height, int color, int radius, String methodName, List<String> methodArgs) {
        super(name, x, y, width, height, color, radius);

        this.methodName = methodName;
        this.methodArgs = methodArgs;
    }

    static Map<String, Consumer<List<String>>> methodMap = new HashMap<>();
    // Create a test method reference (also works with lambdas
    static Consumer<List<String> > abc = System.out::println;

    public static Clickable parse(String nodeName, String[] properties) {
        methodMap.put("abc", abc);
        float x = Float.parseFloat(properties[0]);
        float y = Float.parseFloat(properties[1]);
        float width = Float.parseFloat(properties[2]);
        float height = Float.parseFloat(properties[3]);
        int color = (int) Long.parseLong(properties[4], 16); // Parse HEX to INT (e.x. FF000000 for black)
        int radius = Integer.decode(properties[5]);
        // Get name and args from string
        String mName = properties[6].substring(0, properties[6].indexOf('[')); // abc
        String arguments = properties[6].substring(properties[6].indexOf('[') + 1, properties[6].lastIndexOf(']')); // arg1,arg2,arg3
        List<String> argumentList = new ArrayList<>(Arrays.asList(arguments.split(",")));

        return new Clickable(nodeName, x, y, width, height, color, radius, mName, argumentList);
    }

    public void run() {
        Consumer<List<String>> method = methodMap.get(this.methodName);

        if (method != null) {
            method.accept(this.methodArgs); // Run method with args
        } else {
            System.out.println("Method not found");
        }
    }

    public String toString() {
        return "Clickable{name='" + this.name + '\'' + ", x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", color=" + this.color + ", radius=" + this.radius + ", methodName=" + this.methodName + ", methodArgs=" + this.methodArgs + ", isChild=" + isChild() + ", children=" + this.children + "}";
    }
}
