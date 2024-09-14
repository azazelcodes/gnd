/*     */ package me.azazeldev.gndfiles.gndmain;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Stack;
/*     */ 
/*     */ public class Parser {
/*     */   public static Map<String, String> variables;
/*     */   
/*     */   public static Map<Node, Map<Integer, String>> replacedProperties;
/*     */   
/*     */   public static Map<String, Node> parseFile(String filename) throws IOException {
/*  14 */     variables.put("mouseX", "0");
/*  15 */     variables.put("mouseY", "0");
/*  17 */     BufferedReader reader = new BufferedReader(new FileReader(filename));
/*  18 */     Map<String, Node> nodeMap = new HashMap<>();
/*  19 */     Stack<Node> stack = new Stack<>();
/*     */     String line;
/*  22 */     while ((line = reader.readLine()) != null) {
/*  23 */       line = line.trim();
/*  24 */       if (line.startsWith("/")) {
/*  25 */         stack.pop();
/*     */         continue;
/*     */       } 
/*  26 */       if (line.startsWith("$")) {
/*  27 */         String[] properties = line.split(" = ");
/*  28 */         variables.put(properties[0], properties[1]);
/*     */         continue;
/*     */       } 
/*  30 */       String nodeName = line.substring(0, line.indexOf('('));
/*  31 */       Node node = getLine(line, nodeName);
/*  32 */       if (!stack.isEmpty()) {
/*  33 */         stack.peek().addChild(node);
/*     */       } else {
/*  35 */         nodeMap.put(nodeName, node);
/*     */       } 
/*  37 */       stack.push(node);
/*     */     } 
/*  41 */     reader.close();
/*  42 */     return nodeMap;
/*     */   }
/*     */   
/*     */   private static Node getLine(String line, String nodeName) {
/*  46 */     String[] properties = line.substring(line.indexOf('(') + 1, line.indexOf(')')).split(", ");
/*  47 */     Map<Integer, String> tempReplaced = new HashMap<>();
/*  48 */     int i = 0;
/*  49 */     for (String property : properties) {
/*  50 */       if (property.contains("$")) {
/*  51 */         int csIndex = Math.min(line.indexOf(','), line.indexOf(' '));
/*  52 */         String targetVar = variables.get(line.substring(line.indexOf('$') + 1, (csIndex == -1) ? line.length() : csIndex));
/*  53 */         line.replace(property, targetVar);
/*  54 */         tempReplaced.put(i, targetVar);
/*     */       } 
/*  56 */       i++;
/*     */     } 
/*  58 */     float x = Float.parseFloat(properties[0]);
/*  59 */     float y = Float.parseFloat(properties[1]);
/*  60 */     float width = Float.parseFloat(properties[2]);
/*  61 */     float height = Float.parseFloat(properties[3]);
/*  62 */     int color = Integer.decode(properties[4]);
/*  63 */     int radius = Integer.decode(properties[5]);
/*  64 */     boolean scrollable = Boolean.parseBoolean(properties[6]);
/*  66 */     Node node = new Node(nodeName, x, y, width, height, color, radius, scrollable);
/*  67 */     for (Map.Entry<Integer, String> entry : tempReplaced.entrySet()) {
/*  68 */       Map<Integer, String> newMap = new HashMap<>();
/*  69 */       newMap.put(entry.getKey(), entry.getValue());
/*  71 */       replacedProperties.put(node, newMap);
/*     */     } 
/*  73 */     return node;
/*     */   }
/*     */   
/*     */   public static List<Node> collectAllNodes(Map<String, Node> nodeMap) {
/*  77 */     List<Node> allNodes = new ArrayList<>();
/*  78 */     for (Node node : nodeMap.values())
/*  79 */       traverseNodes(node, allNodes); 
/*  81 */     return allNodes;
/*     */   }
/*     */   
/*     */   private static void traverseNodes(Node node, List<Node> allNodes) {
/*  85 */     allNodes.add(node);
/*  86 */     for (Node child : node.children)
/*  87 */       traverseNodes(child, allNodes); 
/*     */   }
/*     */   
/*     */   public static void setVariable(String variable, String value) {
/*  92 */     if (variables.containsKey(variable)) {
/*  93 */       variables.replace(variable, value);
/*     */     } else {
/*  95 */       System.out.println("Not a valid variable to replace");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void reparseVariables() {
/* 100 */     for (Map.Entry<Node, Map<Integer, String>> nodeEntry : replacedProperties.entrySet()) {
/* 101 */       Node node = nodeEntry.getKey();
/* 102 */       Map<Integer, String> properties = nodeEntry.getValue();
/* 104 */       for (Map.Entry<Integer, String> propertyEntry : properties.entrySet()) {
/* 105 */         Integer propertyKey = propertyEntry.getKey();
/* 106 */         String propertyValue = propertyEntry.getValue();
/* 108 */         node.replaceProperty(propertyKey, propertyValue);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */