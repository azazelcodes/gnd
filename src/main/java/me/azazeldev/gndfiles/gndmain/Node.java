/*    */ package me.azazeldev.gndfiles.gndmain;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Node {
/*    */   String name;
/*    */   
/*    */   float x;
/*    */   
/*    */   float y;
/*    */   
/*    */   float width;
/*    */   
/*    */   float height;
/*    */   
/*    */   int color;
/*    */   
/*    */   int radius;
/*    */   
/*    */   boolean scrollable;
/*    */   
/*    */   List<Node> children;
/*    */   
/*    */   Node parent;
/*    */   
/*    */   public Node(String name, float x, float y, float width, float height, int color, int radius, boolean scrollable) {
/* 19 */     this.name = name;
/* 20 */     this.x = x;
/* 21 */     this.y = y;
/* 22 */     this.width = width;
/* 23 */     this.height = height;
/* 24 */     this.color = color;
/* 25 */     this.radius = radius;
/* 26 */     this.scrollable = scrollable;
/* 27 */     this.children = new ArrayList<>();
/* 28 */     this.parent = null;
/*    */   }
/*    */   
/*    */   public void addChild(Node child) {
/* 32 */     child.parent = this;
/* 33 */     child.x += this.x;
/* 34 */     child.y += this.y;
/* 35 */     this.children.add(child);
/*    */   }
/*    */   
/*    */   public boolean isChild() {
/* 39 */     return (this.parent != null);
/*    */   }
/*    */   
/*    */   public void replaceProperty(int index, String value) {
/*    */     try {
/* 44 */       switch (index) {
/*    */         case 0:
/* 46 */           this.name = value;
/*    */           break;
/*    */         case 1:
/* 49 */           this.x = Float.parseFloat(value);
/*    */           break;
/*    */         case 2:
/* 52 */           this.y = Float.parseFloat(value);
/*    */           break;
/*    */         case 3:
/* 55 */           this.width = Float.parseFloat(value);
/*    */           break;
/*    */         case 4:
/* 58 */           this.height = Float.parseFloat(value);
/*    */           break;
/*    */         case 5:
/* 61 */           this.color = Integer.parseInt(value);
/*    */           break;
/*    */         case 6:
/* 64 */           this.radius = Integer.parseInt(value);
/*    */           break;
/*    */         case 7:
/* 67 */           this.scrollable = Boolean.parseBoolean(value);
/*    */           break;
/*    */         default:
/* 70 */           throw new IllegalArgumentException("Invalid index: " + index);
/*    */       } 
/* 72 */     } catch (NumberFormatException e) {
/* 73 */       System.err.println("Error parsing value: " + value);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 78 */     return "Node{name='" + this.name + '\'' + ", x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", color=" + this.color + ", radius=" + this.radius + ", scrollable=" + this.scrollable + ", isChild=" + 
/*    */       
/* 87 */       isChild() + ", children=" + this.children + '}';
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */