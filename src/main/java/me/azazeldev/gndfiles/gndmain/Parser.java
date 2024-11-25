package me.azazeldev.gndfiles.gndmain;

import me.azazeldev.gndfiles.Main;
import me.azazeldev.gndfiles.gndmain.types.Clickable;
import me.azazeldev.gndfiles.gndmain.types.Instance;
import me.azazeldev.gndfiles.gndmain.types.Node;
import me.azazeldev.gndfiles.gndmain.types.Scrollable;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Parser {
    public static Map<String, String> variables = new HashMap<>();
    public static List<Node> actionables = new ArrayList<>();
    public static Map<Node, Map<Map<String, Integer>, String>> replacedProperties = new HashMap<>();

    // Initially parse a full file
    public static Map<String, Node> parseFile(File file) throws IOException {
        // Base for mouse positions
        variables.put("mouseX", "0");
        variables.put("mouseY", "0");
        // Iterate file
        BufferedReader reader = new BufferedReader(new FileReader(file));
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
                variables.put(properties[0].replace("$", ""), properties[1]);
                continue;
            }
            if (!line.isEmpty() && !line.trim().isEmpty()) {
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
        }
        reader.close();
        return nodeMap;
    }

    private static Node getLine(String line, String nodeName) {
        // Parse properties
        String[] properties = line.substring(line.indexOf('(') + 1, line.indexOf(')')).split(", ");
        Map<Map<String, Integer>, String> tempReplaced = new HashMap<>();
        int i = 0;
        for (String property : properties) {
            // Check for variable
            if (property.contains("$")) {
                int csIndex = Math.min(line.indexOf(',', line.indexOf(property)), line.indexOf(' ', line.indexOf(property)));
                // Gets assigned variable value of the string between $ and , or ' ' (= var name)
                String targetKey = line.substring(line.indexOf('$') + 1, (csIndex == -1) ? line.length() : csIndex).replaceFirst("$", "");
                String targetVar = variables.get(targetKey);
                // Weird thang (Only replacing it once cuz error + incorrect counting)
                line = StringUtils.replaceOnce(line, property, targetVar);
                Map<String, Integer> repmap = new LinkedHashMap<>();
                repmap.put(targetKey, i);
                tempReplaced.put(repmap, targetVar);
            }
            i++;
        }
        // Reparse properties to include updated variables
        properties = line.substring(line.indexOf('(') + 1, line.indexOf(')')).split(", ");
        // Map to node properties
        String type = nodeName.substring(0, 1);
        nodeName = nodeName.substring(1);
        Node node;
        switch (type) {
            case ".":
                // Fall through to default (blank node)
            default:
                node = Node.parse(nodeName, properties);
                break;
            case "@":
                node = Scrollable.parse(nodeName, properties);
                actionables.add(node);
                break;
            case "!":
                node = Clickable.parse(nodeName, properties);
                actionables.add(node);
                break;
            case ">":
                node = Instance.parse(nodeName, properties);
                break;
        }
        for (Map.Entry<Map<String, Integer>, String> entry : tempReplaced.entrySet()) {
            Map<Map<String, Integer>, String> newMap = new HashMap<>();
            newMap.put(entry.getKey(), entry.getValue());
            replacedProperties.put(node, newMap);
        }
        if (Main.test) Main.l.info(node);
        return node;
    }

    public static void setVariable(String variable, String value) {
        if (variables.containsKey(variable)) {
            variables.replace(variable, value);
            Main.l.info(variables);
        } else {
            Main.l.info(variable + " is not a valid variable to replace");
        }
    }

    public static void reparseVariables() {
        for (Map.Entry<Node, Map<Map<String, Integer>, String>> nodeEntry : replacedProperties.entrySet()) {
            Node node = nodeEntry.getKey();
            Map<Map<String, Integer>, String> properties = nodeEntry.getValue();
            for (Map.Entry<Map<String, Integer>, String> propertyEntry : properties.entrySet()) {
                String varname = (String) propertyEntry.getKey().keySet().toArray()[0];
                if (Main.test) Main.l.info(variables.toString());
                if (variables.containsKey(varname)) {
                    Integer propertyKey = (Integer) propertyEntry.getKey().values().toArray()[0];
                    String propertyValue = variables.get(varname);
                    if (Main.test) Main.l.info(node.toString() + "; rep: " + varname + "(" + propertyKey.toString() + ")" + " W " + propertyValue);
                    node.replaceProperty(propertyKey, propertyValue);
                } else {
                    Main.l.info("The map contains the variable name " + varname + " even though it is not a valid variable!");
                }
            }
        }
    }
}