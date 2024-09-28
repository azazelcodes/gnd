package me.azazeldev.gndfiles.gndmain;

import me.azazeldev.gndfiles.gndmain.types.Clickable;
import me.azazeldev.gndfiles.gndmain.types.Node;
import me.azazeldev.gndfiles.gndmain.types.Scrollable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Parser {
    public static Map<String, String> variables;
    public static Map<Node, Map<Integer, String>> replacedProperties;

    // Initially parse a full file
    public static Map<String, Node> parseFile(String filename) throws IOException {
        // Base for mouse positions
        variables.put("mouseX", "0");
        variables.put("mouseY", "0");
        // Iterate file
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        Map<String, Node> nodeMap = new HashMap<>();
        Stack<Node> stack = new Stack<>();
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            // Node end
            if (line.startsWith("/")) {
                stack.pop();
                continue;
            }
            // Variable definition
            if (line.startsWith("$")) {
                String[] properties = line.split(" = ");
                variables.put(properties[0], properties[1]);
                continue;
            }
            // Get node name and parse it
            String nodeName = line.substring(0, line.indexOf('('));
            Node node = getLine(line, nodeName);
            // Add as child if not standalone
            if (!stack.isEmpty()) {
                stack.peek().addChild(node);
            } else {
                nodeMap.put(nodeName, node);
            }
            stack.push(node);
        }
        reader.close();
        return nodeMap;
    }

    private static Node getLine(String line, String nodeName) {
        // Parse properties
        String[] properties = line.substring(line.indexOf('(') + 1, line.indexOf(')')).split(", ");
        Map<Integer, String> tempReplaced = new HashMap<>();
        int i = 0;
        for (String property : properties) {
            // Check for variable
            if (property.contains("$")) {
                int csIndex = Math.min(line.indexOf(','), line.indexOf(' '));
                String targetVar = variables.get(line.substring(line.indexOf('$') + 1, (csIndex == -1) ? line.length() : csIndex));
                line.replace(property, targetVar);
                tempReplaced.put(i, targetVar);
            }
            i++;
        }
        // Map to node properties
        String type = nodeName.substring(0, 1);
        nodeName = nodeName.substring(1);
        Node node;
        switch (type) {
            case ".":
                // Fall through to default (blank node)
            case "@":
                node = Scrollable.parse(nodeName, properties);
                break;
            case "!":
                node = Clickable.parse(nodeName, properties);
                break;
            default:
                node = Node.parse(nodeName, properties);
                break;
        }
        for (Map.Entry<Integer, String> entry : tempReplaced.entrySet()) {
            Map<Integer, String> newMap = new HashMap<>();
            newMap.put(entry.getKey(), entry.getValue());
            replacedProperties.put(node, newMap);
        }
        return node;
    }

    public static List<Node> collectAllNodes(Map<String, Node> nodeMap) {
        List<Node> allNodes = new ArrayList<>();
        for (Node node : nodeMap.values())
            traverseNodes(node, allNodes);
        return allNodes;
    }

    private static void traverseNodes(Node node, List<Node> allNodes) {
        allNodes.add(node);
        for (Node child : node.children)
            traverseNodes(child, allNodes);
    }

    public static void setVariable(String variable, String value) {
        if (variables.containsKey(variable)) {
            variables.replace(variable, value);
        } else {
            System.out.println("Not a valid variable to replace");
        }
    }

    public static void reparseVariables() {
        for (Map.Entry<Node, Map<Integer, String>> nodeEntry : replacedProperties.entrySet()) {
            Node node = nodeEntry.getKey();
            Map<Integer, String> properties = nodeEntry.getValue();
            for (Map.Entry<Integer, String> propertyEntry : properties.entrySet()) {
                Integer propertyKey = propertyEntry.getKey();
                String propertyValue = propertyEntry.getValue();
                node.replaceProperty(propertyKey, propertyValue);
            }
        }
    }
}


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */