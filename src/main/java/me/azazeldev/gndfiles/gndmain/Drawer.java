/*    */ package me.azazeldev.gndfiles.gndmain;
/*    */ 
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class Drawer {
/*    */   public static void draw(Node n, Minecraft mc, ScaledResolution sr, int scrollProgress) {
/* 11 */     if (n.scrollable) {
/* 12 */       GL11.glEnable(3089);
/* 14 */       int scaleFactor = sr.func_78325_e();
/* 15 */       GL11.glScissor((int)(n.x * scaleFactor), (int)(mc.field_71440_d - (n.y + n.height) * scaleFactor), (int)(n.width * scaleFactor), (int)(n.height * scaleFactor));
/*    */     } 
/* 17 */     if (n.radius == 0) {
/* 18 */       RenderUtils.drawRect(n.x, n.y + (n.scrollable ? scrollProgress : false), n.x + n.width, n.y + n.height + (n.scrollable ? scrollProgress : false), n.color);
/* 19 */     } else if (n.radius >= 100) {
/* 20 */       RenderUtils.drawCircle(n.x, n.y, (n.width + n.height) / 2.0F, n.color);
/*    */     } else {
/* 22 */       RenderUtils.drawRoundedRect(n.x, n.y + (n.scrollable ? scrollProgress : false), n.x + n.width, n.y + n.height + (n.scrollable ? scrollProgress : false), n.radius, n.color);
/*    */     } 
/* 26 */     if (!n.children.isEmpty())
/* 27 */       for (Node c : n.children)
/* 28 */         draw(c, mc, sr, scrollProgress);  
/* 31 */     if (n.scrollable)
/* 32 */       GL11.glDisable(3089); 
/*    */   }
/*    */   
/*    */   public static void draw(Map<String, Node> nodeMap, Minecraft mc, ScaledResolution sr, int scrollProgress) {
/* 37 */     for (Node n : nodeMap.values())
/* 38 */       draw(n, mc, sr, scrollProgress); 
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */