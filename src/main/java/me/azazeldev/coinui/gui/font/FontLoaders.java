/*    */ package me.azazeldev.coinui.gui.font;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import java.io.InputStream;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class FontLoaders {
/* 15 */   public static CFontRenderer arial18 = new CFontRenderer(getArial(18), true, true);
/*    */   
/* 16 */   public static CFontRenderer arial24 = new CFontRenderer(getArial(24), true, true);
/*    */   
/* 17 */   public static CFontRenderer roboto16 = new CFontRenderer(getRoboto(), true, true);
/*    */   
/*    */   private static Font getArial(int size) {
/*    */     Font font;
/*    */     try {
/* 23 */       InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("client/Arial.ttf")).func_110527_b();
/* 24 */       font = Font.createFont(0, is);
/* 25 */       font = font.deriveFont(0, size);
/* 26 */     } catch (Exception ex) {
/* 27 */       System.out.println("Error loading font");
/* 28 */       font = new Font("default", 0, size);
/*    */     } 
/* 30 */     return font;
/*    */   }
/*    */   
/*    */   private static Font getRoboto() {
/*    */     Font font;
/*    */     try {
/* 36 */       InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("client/Roboto-Medium.ttf")).func_110527_b();
/* 37 */       font = Font.createFont(0, is);
/* 38 */       font = font.deriveFont(0, 16.0F);
/* 39 */     } catch (Exception ex) {
/* 40 */       System.out.println("Error loading font");
/* 41 */       font = new Font("default", 0, 16);
/*    */     } 
/* 43 */     return font;
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */