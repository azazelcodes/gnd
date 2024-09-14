/*     */ package me.azazeldev.gndfiles.gndmain;
/*     */ 
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class RenderUtils {
/*     */   public static void glColor(int hex) {
/*  15 */     float alpha = (hex >> 24 & 0xFF) / 255.0F;
/*  16 */     float red = (hex >> 16 & 0xFF) / 255.0F;
/*  17 */     float green = (hex >> 8 & 0xFF) / 255.0F;
/*  18 */     float blue = (hex & 0xFF) / 255.0F;
/*  19 */     GL11.glColor4f(red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public static void circle(float x, float y, float radius, int fill) {
/*  23 */     arc(x, y, 0.0F, 360.0F, radius, fill);
/*     */   }
/*     */   
/*     */   public static void drawCircle(float x, float y, float radius, int color) {
/*  27 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/*  28 */     float red = (color >> 16 & 0xFF) / 255.0F;
/*  29 */     float green = (color >> 8 & 0xFF) / 255.0F;
/*  30 */     float blue = (color & 0xFF) / 255.0F;
/*  33 */     GL11.glColor4f(red, green, blue, alpha);
/*  34 */     GL11.glEnable(3042);
/*  35 */     GL11.glDisable(3553);
/*  36 */     GL11.glBlendFunc(770, 771);
/*  37 */     GL11.glEnable(2848);
/*  38 */     GL11.glPushMatrix();
/*  39 */     GL11.glLineWidth(1.0F);
/*  40 */     GL11.glBegin(9);
/*  41 */     for (int i = 0; i <= 360; i++)
/*  42 */       GL11.glVertex2d(x + Math.sin(i * Math.PI / 180.0D) * radius, y + Math.cos(i * Math.PI / 180.0D) * radius); 
/*  43 */     GL11.glEnd();
/*  44 */     GL11.glPopMatrix();
/*  45 */     GL11.glEnable(3553);
/*  46 */     GL11.glDisable(2848);
/*  47 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static void arc(float x, float y, float start, float end, float radius, int color) {
/*  54 */     arcEllipse(x, y, start, end, radius, radius, color);
/*     */   }
/*     */   
/*     */   public static void arcEllipse(float x, float y, float start, float end, float w, float h, int color) {
/*  59 */     GlStateManager.func_179124_c(0.0F, 0.0F, 0.0F);
/*  60 */     GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.0F);
/*  61 */     float temp = 0.0F;
/*  62 */     if (start > end) {
/*  63 */       temp = end;
/*  64 */       end = start;
/*  65 */       start = temp;
/*     */     } 
/*  67 */     float var11 = (color >> 24 & 0xFF) / 255.0F;
/*  68 */     float var12 = (color >> 16 & 0xFF) / 255.0F;
/*  69 */     float var13 = (color >> 8 & 0xFF) / 255.0F;
/*  70 */     float var14 = (color & 0xFF) / 255.0F;
/*  71 */     Tessellator var15 = Tessellator.func_178181_a();
/*  72 */     var15.func_178180_c();
/*  73 */     GlStateManager.func_179147_l();
/*  74 */     GlStateManager.func_179090_x();
/*  75 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/*  76 */     GlStateManager.func_179131_c(var12, var13, var14, var11);
/*  77 */     if (var11 > 0.5F) {
/*  78 */       GL11.glEnable(2848);
/*  79 */       GL11.glLineWidth(2.0F);
/*  80 */       GL11.glBegin(3);
/*  81 */       for (float f = end; f >= start; f -= 4.0F) {
/*  82 */         float ldx = (float)Math.cos(f * Math.PI / 180.0D) * w * 1.001F;
/*  83 */         float ldy = (float)Math.sin(f * Math.PI / 180.0D) * h * 1.001F;
/*  84 */         GL11.glVertex2f(x + ldx, y + ldy);
/*     */       } 
/*  86 */       GL11.glEnd();
/*  87 */       GL11.glDisable(2848);
/*     */     } 
/*  89 */     GL11.glBegin(6);
/*  90 */     for (float i = end; i >= start; i -= 4.0F) {
/*  91 */       float ldx = (float)Math.cos(i * Math.PI / 180.0D) * w;
/*  92 */       float ldy = (float)Math.sin(i * Math.PI / 180.0D) * h;
/*  93 */       GL11.glVertex2f(x + ldx, y + ldy);
/*     */     } 
/*  95 */     GL11.glEnd();
/*  96 */     GlStateManager.func_179098_w();
/*  97 */     GlStateManager.func_179084_k();
/*     */   }
/*     */   
/*     */   public static void drawRect(float x1, float y1, float x2, float y2, int color) {
/* 101 */     Gui.func_73734_a((int)x1, (int)y1, (int)x2, (int)y2, color);
/*     */   }
/*     */   
/*     */   public static void drawRoundedRect(float x1, float y1, float x2, float y2, int round, int color) {
/* 105 */     x1 += (float)((round / 2.0F) + 0.5D);
/* 106 */     y1 += (float)((round / 2.0F) + 0.5D);
/* 107 */     x2 -= (float)((round / 2.0F) + 0.5D);
/* 108 */     y2 -= (float)((round / 2.0F) + 0.5D);
/* 109 */     Gui.func_73734_a((int)x1, (int)y1, (int)x2, (int)y2, color);
/* 110 */     circle(x2 - round / 2.0F, y1 + round / 2.0F, round, color);
/* 111 */     circle(x1 + round / 2.0F, y2 - round / 2.0F, round, color);
/* 112 */     circle(x1 + round / 2.0F, y1 + round / 2.0F, round, color);
/* 113 */     circle(x2 - round / 2.0F, y2 - round / 2.0F, round, color);
/* 114 */     Gui.func_73734_a((int)(x1 - round / 2.0F - 0.5F), (int)(y1 + round / 2.0F), (int)x2, (int)(y2 - round / 2.0F), color);
/* 116 */     Gui.func_73734_a((int)x1, (int)(y1 + round / 2.0F), (int)(x2 + round / 2.0F + 0.5F), (int)(y2 - round / 2.0F), color);
/* 118 */     Gui.func_73734_a((int)(x1 + round / 2.0F), (int)(y1 - round / 2.0F - 0.5F), (int)(x2 - round / 2.0F), (int)(y2 - round / 2.0F), color);
/* 120 */     Gui.func_73734_a((int)(x1 + round / 2.0F), (int)y1, (int)(x2 - round / 2.0F), (int)(y2 + round / 2.0F + 0.5F), color);
/*     */   }
/*     */   
/*     */   public static void initAA() throws LWJGLException {
/* 126 */     GL11.glEnable(3042);
/* 127 */     GL11.glBlendFunc(770, 771);
/*     */   }
/*     */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */