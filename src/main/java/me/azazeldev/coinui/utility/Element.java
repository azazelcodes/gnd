/*     */ package me.azazeldev.coinui.utility;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.Clipboard;
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.datatransfer.StringSelection;
/*     */ import java.awt.datatransfer.UnsupportedFlavorException;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import me.azazeldev.coinui.Main;
/*     */ import me.azazeldev.coinui.gui.Theme;
/*     */ import me.azazeldev.coinui.gui.font.FontLoaders;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ public class Element {
/*     */   int lastRememberedKeycode;
/*     */   
/*     */   public void Empty(Float posX, Float posY, String title, String description, boolean centeredX, boolean centeredY) {
/*  24 */     posX = centeredX ? (posX + 135.0F) : posX;
/*  25 */     posY = centeredY ? (posY + 22.5F) : posY;
/*  27 */     RenderUtils.drawRoundedRect(posX, posY, posX + 350.0F, posY + 45.0F, 10, Theme.elementColor.getRGB());
/*  28 */     RenderUtils.drawRoundedRect(posX + 225.0F, posY, posX + 350.0F, posY + 45.0F, 10, Theme.overlayColor.getRGB());
/*  29 */     FontLoaders.arial24.drawString(title, posX + 10.0F, posY + 16.0F, Theme.highlightColorB.getRGB());
/*  32 */     int lineHeight = 0;
/*  34 */     List<String> splits = FontLoaders.arial18.wrapWords(description, 160.0D);
/*  35 */     for (String e : splits.subList(0, Math.min(4, splits.size()))) {
/*  36 */       FontLoaders.arial18.drawString(e, posX + 10.0F + FontLoaders.arial24.getStringWidth(title) + 15.0F, posY + 5.0F + lineHeight, Theme.descriptionColor.getRGB());
/*  37 */       lineHeight += 9;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void Toggle(Float posX, Float posY, String title, String description, boolean centeredX, boolean centeredY, int mouseX, int mouseY, String configValueName) {
/*  42 */     if (Config.getConfig().get(configValueName) == null)
/*  43 */       System.out.println("GUI ERROR: " + configValueName + " does not exist in the user's config. Try deleting the config file in %appdata%/.minecraft/NotEnoughCoins/"); 
/*  46 */     posX = centeredX ? (posX + 135.0F) : posX;
/*  47 */     posY = centeredY ? (posY + 22.5F) : posY;
/*  49 */     boolean isHovered = isHovered(posX + 303.0F, posY + 11.25F, posX + 327.0F, posY + 31.5F, mouseX, mouseY);
/*  50 */     boolean configValue = Config.getConfig().get(configValueName).getAsBoolean();
/*  51 */     boolean justClicked = Main.justClicked;
/*  53 */     if (isHovered && Mouse.isButtonDown(0) && !justClicked) {
/*  54 */       Main.justClicked = true;
/*  55 */       Config.write(configValueName, Config.gson.toJsonTree(!configValue));
/*     */     } 
/*  58 */     if (isHovered && !Mouse.isButtonDown(0) && justClicked)
/*  59 */       Main.justClicked = false; 
/*  62 */     float objectLength = 350.0F;
/*  63 */     float objectHeight = 45.0F;
/*  64 */     int objectRoundness = 10;
/*  65 */     float overlayLength = 70.0F;
/*  66 */     int overlayRoundness = 10;
/*  67 */     float titleXoffset = 10.0F;
/*  68 */     float titleYoffset = 16.0F;
/*  70 */     RenderUtils.drawRoundedRect(posX, posY, posX + objectLength, posY + objectHeight, objectRoundness, Theme.elementColor.getRGB());
/*  71 */     RenderUtils.drawRoundedRect(posX + objectLength - overlayLength, posY, posX + objectLength, posY + objectHeight, overlayRoundness, Theme.overlayColor.getRGB());
/*  72 */     FontLoaders.arial24.drawString(title, posX + titleXoffset, posY + titleYoffset, Theme.highlightColorB.getRGB());
/*  74 */     int highlightColor = configValue ? Theme.highlightColorD.getRGB() : Theme.highlightColorA.getRGB();
/*  76 */     RenderUtils.drawCircle(posX + 303.0F, posY + 21.25F, 10.0F, highlightColor);
/*  77 */     RenderUtils.drawCircle(posX + 327.0F, posY + 21.25F, 10.0F, highlightColor);
/*  78 */     RenderUtils.drawRect(posX + 303.0F, posY + 11.0F, posX + 327.0F, posY + 31.75F, highlightColor);
/*  80 */     RenderUtils.drawCircle(posX + 280.0F + (configValue ? 23.0F : 47.0F), posY + 21.0F, 7.5F, Theme.highlightColorB.getRGB());
/*  83 */     int lineHeight = 0;
/*  85 */     List<String> splits = FontLoaders.arial18.wrapWords(description, 160.0D);
/*  86 */     for (String e : splits.subList(0, Math.min(4, splits.size()))) {
/*  87 */       FontLoaders.arial18.drawString(e, posX + 10.0F + FontLoaders.arial24.getStringWidth(title) + 15.0F, posY + 5.0F + lineHeight, Theme.descriptionColor.getRGB());
/*  88 */       lineHeight += 9;
/*     */     } 
/*     */   }
/*     */   
/*  92 */   int marqueeLength = 0;
/*     */   
/*     */   public void TextInput(Float posX, Float posY, String title, String description, boolean centeredX, boolean centeredY, int mouseX, int mouseY, String configValueName, char lastTypedChar, int lastTypedKeycode, boolean onlyAlphabetical, String[] exclusions) {
/*  94 */     if (Config.getConfig().get(configValueName) == null)
/*  95 */       System.out.println("GUI ERROR: " + configValueName + " does not exist in the user's config. Try deleting the config file in %appdata%/.minecraft/NotEnoughCoins/"); 
/*  98 */     String currentValue = Config.getConfig().get(configValueName).getAsString();
/* 100 */     posX = centeredX ? (posX + 135.0F) : posX;
/* 101 */     posY = centeredY ? (posY + 22.5F) : posY;
/* 103 */     float objectLength = 350.0F;
/* 104 */     float objectHeight = 45.0F;
/* 105 */     int objectRoundness = 10;
/* 106 */     float overlayLength = 125.0F;
/* 107 */     int overlayRoundness = 10;
/* 108 */     float titleXoffset = 10.0F;
/* 109 */     float titleYoffset = 16.0F;
/* 111 */     RenderUtils.drawRoundedRect(posX, posY, posX + objectLength, posY + objectHeight, objectRoundness, Theme.elementColor.getRGB());
/* 112 */     RenderUtils.drawRoundedRect(posX + objectLength - overlayLength, posY, posX + objectLength, posY + objectHeight, overlayRoundness, Theme.overlayColor.getRGB());
/* 113 */     FontLoaders.arial24.drawString(title, posX + titleXoffset, posY + titleYoffset, Theme.highlightColorB.getRGB());
/* 117 */     float startOffset = 25.0F;
/* 118 */     float inputLength = 80.0F;
/* 119 */     int maxMarqueeLength = 120;
/* 120 */     RenderUtils.drawRoundedRect(posX + objectLength - overlayLength + startOffset, posY + 11.875F, posX + objectLength - overlayLength + startOffset + inputLength, posY + 34.875F, 1, Theme.categoryColor.getRGB());
/* 122 */     List<String> exclusionList = Arrays.asList(exclusions);
/* 126 */     if (lastTypedKeycode == 29) {
/* 127 */       this.lastRememberedKeycode = 29;
/* 128 */     } else if (lastTypedKeycode == 47 && this.lastRememberedKeycode == 29) {
/* 129 */       paste();
/* 130 */     } else if (lastTypedKeycode == 46 && this.lastRememberedKeycode == 29) {
/* 131 */       copy();
/* 132 */     } else if (lastTypedKeycode != -1) {
/* 133 */       this.lastRememberedKeycode = -1;
/*     */     } 
/* 136 */     if (lastTypedKeycode != -1 && lastTypedKeycode != 184 && lastTypedKeycode != 56 && lastTypedKeycode != 29 && lastTypedKeycode != 14)
/* 137 */       if (onlyAlphabetical) {
/* 138 */         if (Character.isAlphabetic(lastTypedChar) || exclusionList.contains(Character.toString(lastTypedChar)))
/* 139 */           Config.write(configValueName, Config.gson.toJsonTree(currentValue + lastTypedChar)); 
/* 141 */       } else if (!exclusionList.contains(Character.toString(lastTypedChar))) {
/* 142 */         Config.write(configValueName, Config.gson.toJsonTree(currentValue + lastTypedChar));
/*     */       }  
/* 145 */     currentValue = Config.getConfig().get(configValueName).getAsString();
/* 146 */     if (lastTypedKeycode == 14 && !currentValue.equals(""))
/* 147 */       Config.write(configValueName, Config.gson.toJsonTree(currentValue.substring(0, currentValue.length() - 1))); 
/* 150 */     if (lastTypedKeycode == 211 && !currentValue.equals(""))
/* 151 */       Config.write(configValueName, Config.gson.toJsonTree("")); 
/* 155 */     currentValue = Config.getConfig().get(configValueName).getAsString();
/* 156 */     if (!currentValue.equals("")) {
/* 157 */       String shownValue = currentValue;
/* 158 */       while (FontLoaders.arial18.getStringWidth(shownValue) + posX > posX + 70.0F) {
/* 159 */         if (isHovered(posX + 255.0F, posY + 11.0F, posX + 325.0F, posY + 32.75F, mouseX, mouseY)) {
/* 161 */           int startIndex = this.marqueeLength % currentValue.length();
/* 164 */           int endIndex = (this.marqueeLength + currentValue.length() - 1 / maxMarqueeLength) % currentValue.length();
/* 167 */           if (startIndex <= endIndex) {
/* 168 */             shownValue = currentValue.substring(startIndex, endIndex + 1);
/*     */           } else {
/* 170 */             shownValue = currentValue.substring(startIndex) + currentValue.substring(0, endIndex + 1);
/*     */           } 
/* 174 */           this.marqueeLength++;
/* 177 */           if (this.marqueeLength == maxMarqueeLength + 1)
/* 178 */             this.marqueeLength = 0; 
/*     */           continue;
/*     */         } 
/* 181 */         shownValue = shownValue.substring(1);
/*     */       } 
/* 184 */       FontLoaders.arial18.drawString(shownValue, posX + 255.0F, posY + 18.875F, Theme.descriptionColor.getRGB());
/*     */     } else {
/* 186 */       FontLoaders.arial18.drawString("Enter " + title + "!", posX + 255.0F, posY + 18.875F, Theme.descriptionColor.getRGB());
/*     */     } 
/* 190 */     int lineHeight = 0;
/* 192 */     List<String> splits = FontLoaders.arial18.wrapWords(description, 160.0D);
/* 193 */     for (String e : splits.subList(0, Math.min(4, splits.size()))) {
/* 194 */       FontLoaders.arial18.drawString(e, posX + 10.0F + FontLoaders.arial24.getStringWidth(title) + 15.0F, posY + 5.0F + lineHeight, Theme.descriptionColor.getRGB());
/* 195 */       lineHeight += 9;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void Slider(float posX, float posY, String title, String description, boolean centeredX, boolean centeredY, int mouseX, int mouseY, String configValueName) {
/* 199 */     if (Config.getConfig().get(configValueName) == null)
/* 200 */       System.out.println("GUI ERROR: " + configValueName + " does not exist in the user's config. Try deleting the config file in %appdata%/.minecraft/NotEnoughCoins/"); 
/* 202 */     float min = (float)Config.getConfig().get(configValueName).getAsJsonObject().get("min").getAsDouble();
/* 203 */     float max = (float)Config.getConfig().get(configValueName).getAsJsonObject().get("max").getAsDouble();
/* 204 */     int step = Config.getConfig().get(configValueName).getAsJsonObject().get("step").getAsInt();
/* 207 */     posX = centeredX ? (posX + 135.0F) : posX;
/* 208 */     posY = centeredY ? (posY + 22.5F) : posY;
/* 210 */     float objectLength = 350.0F;
/* 211 */     float objectHeight = 45.0F;
/* 212 */     int objectRoundness = 10;
/* 213 */     float overlayLength = 125.0F;
/* 214 */     int overlayRoundness = 10;
/* 215 */     float titleXoffset = 10.0F;
/* 216 */     float titleYoffset = 16.0F;
/* 218 */     RenderUtils.drawRoundedRect(posX, posY, posX + objectLength, posY + objectHeight, objectRoundness, Theme.elementColor.getRGB());
/* 219 */     RenderUtils.drawRoundedRect(posX + objectLength - overlayLength, posY, posX + objectLength, posY + objectHeight, overlayRoundness, Theme.overlayColor.getRGB());
/* 220 */     FontLoaders.arial24.drawString(title, posX + titleXoffset, posY + titleYoffset, Theme.highlightColorB.getRGB());
/* 223 */     float barStartX = posX + 253.0F;
/* 224 */     float barEndX = posX + 327.0F;
/* 225 */     float barCenterY = posY + 21.25F;
/* 226 */     RenderUtils.drawCircle(barStartX - 1.0F, barCenterY, 10.0F, Theme.highlightColorA.getRGB());
/* 227 */     RenderUtils.drawCircle(barEndX - 1.0F, barCenterY, 10.0F, Theme.highlightColorA.getRGB());
/* 228 */     RenderUtils.drawRect(barStartX, posY + 11.0F, barEndX, posY + 31.75F, Theme.highlightColorA.getRGB());
/* 231 */     float sliderPos = posX + 245.0F + ((int)Config.getConfig().get(configValueName).getAsJsonObject().get("value").getAsDouble() - min) / (max - min) * 94.0F;
/* 234 */     float sliderRadius = 2.5F;
/* 235 */     RenderUtils.drawCircle(sliderPos, posY + 9.5F, sliderRadius, Theme.highlightColorB.getRGB());
/* 236 */     RenderUtils.drawCircle(sliderPos, posY + 37.0F, sliderRadius, Theme.highlightColorB.getRGB());
/* 237 */     RenderUtils.drawRect(sliderPos - sliderRadius, posY + 9.5F, sliderPos + 2.25F, posY + 37.0F, Theme.highlightColorA.getRGB());
/* 239 */     double newValue = ((sliderPos + mouseX - sliderPos - posX + 244.0F) / 94.0F / (max - min) * step + min);
/* 241 */     if (isHovered(posX + 244.0F, posY, posX + 338.0F, posY + 45.0F, mouseX, mouseY) && Mouse.isButtonDown(0) && newValue < max)
/* 242 */       Config.write(configValueName, new Gson().<JsonObject>fromJson("{\"value\":" + newValue + ",\"min\":" + min + ",\"max\":" + max + ",\"step\":" + step + "}", JsonObject.class)); 
/* 246 */     double currentValue = Math.round(Config.getConfig().get(configValueName).getAsJsonObject().get("value").getAsDouble());
/* 247 */     String formattedValue = MainUtils.formatNumber(currentValue);
/* 248 */     int valueWidth = FontLoaders.arial18.getStringWidth(formattedValue);
/* 249 */     FontLoaders.arial18.drawString(formattedValue, posX + 335.0F - valueWidth, posY + 35.0F, Theme.descriptionColor.getRGB());
/* 252 */     int lineHeight = 0;
/* 254 */     List<String> splits = FontLoaders.arial18.wrapWords(description, 160.0D);
/* 255 */     for (String e : splits.subList(0, Math.min(4, splits.size()))) {
/* 256 */       FontLoaders.arial18.drawString(e, posX + 10.0F + FontLoaders.arial24.getStringWidth(title) + 15.0F, posY + 5.0F + lineHeight, Theme.descriptionColor.getRGB());
/* 257 */       lineHeight += 9;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
/* 262 */     return (mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2);
/*     */   }
/*     */   
/*     */   private void copy() {
/* 266 */     Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 267 */     String text = "value";
/* 268 */     StringSelection selection = new StringSelection(text);
/* 269 */     clipboard.setContents(selection, null);
/*     */   }
/*     */   
/*     */   private void paste() {
/* 273 */     Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 274 */     DataFlavor flavor = DataFlavor.stringFlavor;
/* 275 */     if (clipboard.isDataFlavorAvailable(flavor))
/*     */       try {
/* 277 */         String text = (String)clipboard.getData(flavor);
/* 278 */         System.out.println(text);
/* 279 */       } catch (UnsupportedFlavorException|java.io.IOException e) {
/* 280 */         System.out.println(e);
/*     */       }  
/*     */   }
/*     */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */