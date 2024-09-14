/*     */ package me.azazeldev.gndfiles.gui.font;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class CFont {
/*  14 */   protected CharData[] charData = new CharData[256];
/*     */   
/*     */   protected Font font;
/*     */   
/*     */   protected boolean antiAlias;
/*     */   
/*     */   protected boolean fractionalMetrics;
/*     */   
/*  18 */   protected int fontHeight = -1;
/*     */   
/*  19 */   protected int charOffset = 0;
/*     */   
/*     */   protected DynamicTexture tex;
/*     */   
/*     */   public CFont(Font font, boolean antiAlias, boolean fractionalMetrics) {
/*  23 */     this.font = font;
/*  24 */     this.antiAlias = antiAlias;
/*  25 */     this.fractionalMetrics = fractionalMetrics;
/*  26 */     this.tex = setupTexture(font, antiAlias, fractionalMetrics, this.charData);
/*     */   }
/*     */   
/*     */   protected DynamicTexture setupTexture(Font font, boolean antiAlias, boolean fractionalMetrics, CharData[] chars) {
/*  30 */     BufferedImage img = generateFontImage(font, antiAlias, fractionalMetrics, chars);
/*     */     try {
/*  32 */       return new DynamicTexture(img);
/*  34 */     } catch (Exception e) {
/*  35 */       e.printStackTrace();
/*  36 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected BufferedImage generateFontImage(Font font, boolean antiAlias, boolean fractionalMetrics, CharData[] chars) {
/*  41 */     int imgSize = 512;
/*  42 */     BufferedImage bufferedImage = new BufferedImage(imgSize, imgSize, 2);
/*  43 */     Graphics2D g = (Graphics2D)bufferedImage.getGraphics();
/*  44 */     g.setFont(font);
/*  45 */     g.setColor(new Color(255, 255, 255, 0));
/*  46 */     g.fillRect(0, 0, imgSize, imgSize);
/*  47 */     g.setColor(Color.WHITE);
/*  48 */     g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, fractionalMetrics ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
/*  49 */     g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, antiAlias ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
/*  50 */     g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
/*  51 */     FontMetrics fontMetrics = g.getFontMetrics();
/*  52 */     int charHeight = 0;
/*  53 */     int positionX = 0;
/*  54 */     int positionY = 1;
/*  55 */     int i = 0;
/*  56 */     while (i < chars.length) {
/*  57 */       char ch = (char)i;
/*  58 */       CharData charData = new CharData();
/*  59 */       Rectangle2D dimensions = fontMetrics.getStringBounds(String.valueOf(ch), g);
/*  60 */       charData.width = (dimensions.getBounds()).width + 8;
/*  61 */       charData.height = (dimensions.getBounds()).height;
/*  62 */       if (positionX + charData.width >= imgSize) {
/*  63 */         positionX = 0;
/*  64 */         positionY += charHeight;
/*  65 */         charHeight = 0;
/*     */       } 
/*  67 */       if (charData.height > charHeight)
/*  68 */         charHeight = charData.height; 
/*  70 */       charData.storedX = positionX;
/*  71 */       charData.storedY = positionY;
/*  72 */       if (charData.height > this.fontHeight)
/*  73 */         this.fontHeight = charData.height; 
/*  75 */       chars[i] = charData;
/*  76 */       g.drawString(String.valueOf(ch), positionX + 2, positionY + fontMetrics.getAscent());
/*  77 */       positionX += charData.width;
/*  78 */       i++;
/*     */     } 
/*  80 */     return bufferedImage;
/*     */   }
/*     */   
/*     */   public void drawChar(CharData[] chars, char c, float x, float y) throws ArrayIndexOutOfBoundsException {
/*     */     try {
/*  85 */       drawQuad(x, y, (chars[c]).width, (chars[c]).height, (chars[c]).storedX, (chars[c]).storedY, (chars[c]).width, (chars[c]).height);
/*  87 */     } catch (Exception e) {
/*  88 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void drawQuad(float x, float y, float width, float height, float srcX, float srcY, float srcWidth, float srcHeight) {
/*  93 */     float renderSRCX = srcX / 512.0F;
/*  94 */     float renderSRCY = srcY / 512.0F;
/*  95 */     float renderSRCWidth = srcWidth / 512.0F;
/*  96 */     float renderSRCHeight = srcHeight / 512.0F;
/*  97 */     GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
/*  98 */     GL11.glVertex2d((x + width), y);
/*  99 */     GL11.glTexCoord2f(renderSRCX, renderSRCY);
/* 100 */     GL11.glVertex2d(x, y);
/* 101 */     GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
/* 102 */     GL11.glVertex2d(x, (y + height));
/* 103 */     GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
/* 104 */     GL11.glVertex2d(x, (y + height));
/* 105 */     GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
/* 106 */     GL11.glVertex2d((x + width), (y + height));
/* 107 */     GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
/* 108 */     GL11.glVertex2d((x + width), y);
/*     */   }
/*     */   
/*     */   public int getStringWidth(String text) {
/* 112 */     int width = 0;
/* 113 */     char[] arc = text.toCharArray();
/* 114 */     int n = arc.length;
/* 115 */     int n2 = 0;
/* 116 */     while (n2 < n) {
/* 117 */       char c = arc[n2];
/* 118 */       if (c < this.charData.length)
/* 119 */         width += (this.charData[c]).width - 8 + this.charOffset; 
/* 121 */       n2++;
/*     */     } 
/* 123 */     return width / 2;
/*     */   }
/*     */   
/*     */   public void setAntiAlias(boolean antiAlias) {
/* 127 */     if (this.antiAlias != antiAlias) {
/* 128 */       this.antiAlias = antiAlias;
/* 129 */       this.tex = setupTexture(this.font, antiAlias, this.fractionalMetrics, this.charData);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setFractionalMetrics(boolean fractionalMetrics) {
/* 134 */     if (this.fractionalMetrics != fractionalMetrics) {
/* 135 */       this.fractionalMetrics = fractionalMetrics;
/* 136 */       this.tex = setupTexture(this.font, this.antiAlias, fractionalMetrics, this.charData);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setFont(Font font) {
/* 141 */     this.font = font;
/* 142 */     this.tex = setupTexture(font, this.antiAlias, this.fractionalMetrics, this.charData);
/*     */   }
/*     */   
/*     */   protected static class CharData {
/*     */     public int width;
/*     */     
/*     */     public int height;
/*     */     
/*     */     public int storedX;
/*     */     
/*     */     public int storedY;
/*     */   }
/*     */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */