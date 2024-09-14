/*     */ package me.azazeldev.coinui.gui.elements;
/*     */ 
/*     */ import java.util.List;
/*     */ import me.azazeldev.coinui.Main;
/*     */ import me.azazeldev.coinui.gui.Theme;
/*     */ import me.azazeldev.coinui.gui.font.FontLoaders;
/*     */ import me.azazeldev.coinui.utility.Config;
/*     */ import me.azazeldev.coinui.utility.MainUtils;
/*     */ import me.azazeldev.coinui.utility.RenderUtils;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ public class Element {
/*     */   public void Empty(Float posX, Float posY, String title, String description, boolean centeredX, boolean centeredY) {
/*  17 */     posX = centeredX ? (posX + 135.0F) : posX;
/*  18 */     posY = centeredY ? (posY + 22.5F) : posY;
/*  20 */     RenderUtils.drawRoundedRect(posX, posY, posX + 350.0F, posY + 45.0F, 10, Theme.elementColor.getRGB());
/*  21 */     RenderUtils.drawRoundedRect(posX + 280.0F, posY, posX + 350.0F, posY + 45.0F, 10, Theme.overlayColor.getRGB());
/*  22 */     FontLoaders.arial24.drawString(title, posX + 50.0F, posY + 16.0F, Theme.highlightColorB.getRGB());
/*  24 */     int lineheight = 0;
/*  25 */     int lines = 0;
/*  27 */     List<String> splits = FontLoaders.arial18.wrapWords(description, 120.0D);
/*  28 */     for (String e : splits) {
/*  29 */       if (lines >= 3)
/*     */         break; 
/*  32 */       FontLoaders.arial18.drawString(e, posX + 50.0F + FontLoaders.arial24.getStringWidth(title) + 15.0F, posY + 10.0F + lineheight, Theme.descriptionColor.getRGB());
/*  33 */       lineheight += 9;
/*  34 */       lines++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void Toggle(Float posX, Float posY, String title, String description, boolean centeredX, boolean centeredY, int mouseX, int mouseY, String configValueName) {
/*  39 */     posX = centeredX ? (posX + 135.0F) : posX;
/*  40 */     posY = centeredY ? (posY + 22.5F) : posY;
/*  42 */     boolean isHovered = isHovered(posX + 303.0F, posY + 11.25F, posX + 327.0F, posY + 31.5F, mouseX, mouseY);
/*  43 */     boolean configValue = Config.getConfig().get(configValueName).getAsBoolean();
/*  44 */     boolean justClicked = Main.justClicked;
/*  46 */     if (isHovered && Mouse.isButtonDown(0) && !justClicked) {
/*  47 */       Main.justClicked = true;
/*  48 */       Config.write(configValueName, Config.gson.toJsonTree(!configValue));
/*     */     } 
/*  51 */     if (isHovered && !Mouse.isButtonDown(0) && justClicked)
/*  52 */       Main.justClicked = false; 
/*  55 */     RenderUtils.drawRoundedRect(posX, posY, posX + 350.0F, posY + 45.0F, 10, Theme.elementColor.getRGB());
/*  56 */     RenderUtils.drawRoundedRect(posX + 280.0F, posY, posX + 350.0F, posY + 45.0F, 10, Theme.overlayColor.getRGB());
/*  57 */     FontLoaders.arial24.drawString(title, posX + 50.0F, posY + 16.0F, Theme.highlightColorB.getRGB());
/*  59 */     int highlightColor = configValue ? Theme.highlightColorD.getRGB() : Theme.highlightColorA.getRGB();
/*  61 */     RenderUtils.drawCircle(posX + 303.0F, posY + 21.25F, 10.0F, highlightColor);
/*  62 */     RenderUtils.drawCircle(posX + 327.0F, posY + 21.25F, 10.0F, highlightColor);
/*  63 */     RenderUtils.drawRect(posX + 303.0F, posY + 11.0F, posX + 327.0F, posY + 31.75F, highlightColor);
/*  65 */     RenderUtils.drawCircle(posX + 280.0F + (configValue ? 23.0F : 47.0F), posY + 21.0F, 7.5F, Theme.highlightColorB.getRGB());
/*  67 */     int lineheight = 0;
/*  68 */     int lines = 0;
/*  70 */     List<String> splits = FontLoaders.arial18.wrapWords(description, 120.0D);
/*  71 */     for (String e : splits.subList(0, Math.min(3, splits.size()))) {
/*  72 */       FontLoaders.arial18.drawString(e, posX + 50.0F + FontLoaders.arial24.getStringWidth(title) + 15.0F, posY + 10.0F + lineheight, Theme.descriptionColor.getRGB());
/*  73 */       lineheight += 9;
/*  74 */       lines++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void IntInput(float posX, float posY, String title, String description, boolean centeredX, boolean centeredY, int mouseX, int mouseY, String configValueName, int min, int max, int step) {
/*  79 */     posX = centeredX ? (posX + 135.0F) : posX;
/*  80 */     posY = centeredY ? (posY + 22.5F) : posY;
/*  83 */     RenderUtils.drawRoundedRect(posX, posY, posX + 350.0F, posY + 45.0F, 10, Theme.elementColor.getRGB());
/*  84 */     RenderUtils.drawRoundedRect(posX + 230.0F, posY, posX + 350.0F, posY + 45.0F, 10, Theme.overlayColor.getRGB());
/*  87 */     FontLoaders.arial24.drawString(title, posX + 50.0F, posY + 16.0F, Theme.highlightColorB.getRGB());
/*  90 */     float barStartX = posX + 253.0F;
/*  91 */     float barEndX = posX + 327.0F;
/*  92 */     float barCenterY = posY + 21.25F;
/*  93 */     RenderUtils.drawCircle(barStartX, barCenterY, 10.0F, Theme.highlightColorA.getRGB());
/*  94 */     RenderUtils.drawCircle(barEndX, barCenterY, 10.0F, Theme.highlightColorA.getRGB());
/*  95 */     RenderUtils.drawRect(barStartX, posY + 11.0F, barEndX, posY + 31.75F, Theme.highlightColorA.getRGB());
/*  98 */     float sliderPos = posX + 245.0F + (Config.getConfig().get(configValueName).getAsInt() - min) / (max - min) * 88.0F;
/* 101 */     float sliderRadius = 2.5F;
/* 102 */     RenderUtils.drawCircle(sliderPos, posY + 9.5F, sliderRadius, Theme.highlightColorB.getRGB());
/* 103 */     RenderUtils.drawCircle(sliderPos, posY + 37.0F, sliderRadius, Theme.highlightColorB.getRGB());
/* 104 */     RenderUtils.drawRect(sliderPos - sliderRadius, posY + 9.5F, sliderPos + 2.25F, posY + 37.0F, Theme.highlightColorA.getRGB());
/* 106 */     int newValue = (int)((sliderPos + mouseX - sliderPos - posX + 245.0F) / 88.0F / (max - min) * step) + min;
/* 108 */     if (isHovered(posX + 240.0F, posY, posX + 350.0F, posY + 45.0F, mouseX, mouseY) && Mouse.isButtonDown(0) && newValue < max)
/* 109 */       Config.write(configValueName, Config.gson.toJsonTree(newValue)); 
/* 113 */     int currentValue = Config.getConfig().get(configValueName).getAsInt();
/* 114 */     String formattedValue = MainUtils.formatNumber(currentValue);
/* 115 */     int valueWidth = FontLoaders.arial24.getStringWidth(formattedValue);
/* 116 */     FontLoaders.arial18.drawString(formattedValue, posX + 335.0F - valueWidth, posY + 35.0F, Theme.descriptionColor.getRGB());
/* 119 */     int lineHeight = 0;
/* 120 */     int lines = 0;
/* 121 */     List<String> wrappedDescription = FontLoaders.arial18.wrapWords(description, 95.0D);
/* 122 */     for (String line : wrappedDescription) {
/* 123 */       if (lines < 3) {
/* 124 */         FontLoaders.arial18.drawString(line, posX + 50.0F + FontLoaders.arial24.getStringWidth(title) + 15.0F, posY + 10.0F + lineHeight, Theme.descriptionColor.getRGB());
/* 125 */         lineHeight += 9;
/* 126 */         lines++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
/* 134 */     return (mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2);
/*     */   }
/*     */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */