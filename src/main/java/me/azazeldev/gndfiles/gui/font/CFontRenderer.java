/*     */ package me.azazeldev.gndfiles.gui.font;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.azazeldev.gndfiles.gndmain.RenderUtils;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class CFontRenderer extends CFont {
/*  20 */   protected CFont.CharData[] boldChars = new CFont.CharData[256];
/*     */   
/*  21 */   protected CFont.CharData[] italicChars = new CFont.CharData[256];
/*     */   
/*  22 */   protected CFont.CharData[] boldItalicChars = new CFont.CharData[256];
/*     */   
/*  23 */   private final int[] colorCode = new int[32];
/*     */   
/*  24 */   private final String colorcodeIdentifiers = "0123456789abcdefklmnor";
/*     */   
/*     */   protected DynamicTexture texBold;
/*     */   
/*     */   protected DynamicTexture texItalic;
/*     */   
/*     */   protected DynamicTexture texItalicBold;
/*     */   
/*     */   public CFontRenderer(Font font, boolean antiAlias, boolean fractionalMetrics) {
/*  30 */     super(font, antiAlias, fractionalMetrics);
/*  31 */     setupMinecraftColorcodes();
/*  32 */     setupBoldItalicIDs();
/*     */   }
/*     */   
/*     */   public float drawStringWithShadow(String text, double x, double y, int color) {
/*  36 */     float shadowWidth = drawString(text, x + 0.5D, y + 0.5D, color, true);
/*  37 */     return Math.max(shadowWidth, drawString(text, x, y, color, false));
/*     */   }
/*     */   
/*     */   public float drawString(String text, float x, float y, int color) {
/*  41 */     RenderUtils.drawRect(0.0F, 0.0F, 0.0F, 0.0F, -1);
/*  42 */     return drawString(text, x, y, color, false);
/*     */   }
/*     */   
/*     */   public float drawCenteredString(String text, float x, float y, int color) {
/*  46 */     return drawString(text, x - (getStringWidth(text) / 2), y, color);
/*     */   }
/*     */   
/*     */   public float drawCenteredStringWithShadow(String text, float x, float y, int color) {
/*  50 */     return drawStringWithShadow(text, (x - (getStringWidth(text) / 2)), y, color);
/*     */   }
/*     */   
/*     */   public float drawCenteredStringWithShadow(String text, double x, double y, int color) {
/*  54 */     return drawStringWithShadow(text, x - (getStringWidth(text) / 2), y, color);
/*     */   }
/*     */   
/*     */   public float drawString(String text, double x, double y, int color, boolean shadow) {
/*  58 */     x--;
/*  59 */     if (text == null)
/*  60 */       return 0.0F; 
/*  62 */     if (color == 553648127)
/*  63 */       color = 16777215; 
/*  65 */     if ((color & 0xFC000000) == 0)
/*  66 */       color |= 0xFF000000; 
/*  68 */     if (shadow) {
/*  69 */       color = (color & 0xFCFCFC) >> 2 | color & 0xFF000000;
/*     */     } else {
/*  71 */       RenderUtils.glColor(color);
/*     */     } 
/*  73 */     CFont.CharData[] currentData = this.charData;
/*  74 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/*  75 */     boolean randomCase = false;
/*  76 */     boolean bold = false;
/*  77 */     boolean italic = false;
/*  78 */     boolean strikethrough = false;
/*  79 */     boolean underline = false;
/*  80 */     boolean render = true;
/*  81 */     x *= 2.0D;
/*  82 */     y = (y - 3.0D) * 2.0D;
/*  83 */     if (render) {
/*  84 */       GL11.glPushMatrix();
/*  85 */       GlStateManager.func_179139_a(0.5D, 0.5D, 0.5D);
/*  86 */       GlStateManager.func_179147_l();
/*  87 */       GlStateManager.func_179112_b(770, 771);
/*  88 */       GlStateManager.func_179131_c((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, alpha);
/*  89 */       int size = text.length();
/*  90 */       GlStateManager.func_179098_w();
/*  91 */       GlStateManager.func_179144_i(this.tex.func_110552_b());
/*  92 */       GL11.glBindTexture(3553, this.tex.func_110552_b());
/*  93 */       int i = 0;
/*  94 */       while (i < size) {
/*  95 */         char character = text.charAt(i);
/*  96 */         if (character == '§' && i < size) {
/*  97 */           int colorIndex = 21;
/*     */           try {
/*  99 */             colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(i + 1));
/* 101 */           } catch (Exception e) {
/* 102 */             e.printStackTrace();
/*     */           } 
/* 104 */           if (colorIndex < 16) {
/* 105 */             bold = false;
/* 106 */             italic = false;
/* 107 */             randomCase = false;
/* 108 */             underline = false;
/* 109 */             strikethrough = false;
/* 110 */             GlStateManager.func_179144_i(this.tex.func_110552_b());
/* 111 */             currentData = this.charData;
/* 112 */             if (colorIndex < 0 || colorIndex > 15)
/* 113 */               colorIndex = 15; 
/* 115 */             if (shadow)
/* 116 */               colorIndex += 16; 
/* 118 */             int colorcode = this.colorCode[colorIndex];
/* 119 */             GlStateManager.func_179131_c((colorcode >> 16 & 0xFF) / 255.0F, (colorcode >> 8 & 0xFF) / 255.0F, (colorcode & 0xFF) / 255.0F, alpha);
/* 120 */           } else if (colorIndex == 16) {
/* 121 */             randomCase = true;
/* 122 */           } else if (colorIndex == 17) {
/* 123 */             bold = true;
/* 124 */             if (italic) {
/* 125 */               GlStateManager.func_179144_i(this.texItalicBold.func_110552_b());
/* 126 */               currentData = this.boldItalicChars;
/*     */             } else {
/* 128 */               GlStateManager.func_179144_i(this.texBold.func_110552_b());
/* 129 */               currentData = this.boldChars;
/*     */             } 
/* 131 */           } else if (colorIndex == 18) {
/* 132 */             strikethrough = true;
/* 133 */           } else if (colorIndex == 19) {
/* 134 */             underline = true;
/* 135 */           } else if (colorIndex == 20) {
/* 136 */             italic = true;
/* 137 */             if (bold) {
/* 138 */               GlStateManager.func_179144_i(this.texItalicBold.func_110552_b());
/* 139 */               currentData = this.boldItalicChars;
/*     */             } else {
/* 141 */               GlStateManager.func_179144_i(this.texItalic.func_110552_b());
/* 142 */               currentData = this.italicChars;
/*     */             } 
/* 144 */           } else if (colorIndex == 21) {
/* 145 */             bold = false;
/* 146 */             italic = false;
/* 147 */             randomCase = false;
/* 148 */             underline = false;
/* 149 */             strikethrough = false;
/* 150 */             GlStateManager.func_179131_c((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, alpha);
/* 151 */             GlStateManager.func_179144_i(this.tex.func_110552_b());
/* 152 */             currentData = this.charData;
/*     */           } 
/* 154 */           i++;
/* 155 */         } else if (character < currentData.length && character >= '\000') {
/* 156 */           GL11.glBegin(4);
/* 157 */           drawChar(currentData, character, (float)x, (float)y);
/* 158 */           GL11.glEnd();
/* 159 */           if (strikethrough)
/* 160 */             drawLine(x, y + ((currentData[character]).height / 2), x + (currentData[character]).width - 8.0D, y + ((currentData[character]).height / 2), 1.0F); 
/* 162 */           if (underline)
/* 163 */             drawLine(x, y + (currentData[character]).height - 2.0D, x + (currentData[character]).width - 8.0D, y + (currentData[character]).height - 2.0D, 1.0F); 
/* 165 */           x += ((currentData[character]).width - 8 + this.charOffset);
/*     */         } 
/* 167 */         i++;
/*     */       } 
/* 169 */       GL11.glHint(3155, 4352);
/* 170 */       GL11.glPopMatrix();
/*     */     } 
/* 172 */     return (float)x / 2.0F;
/*     */   }
/*     */   
/*     */   public int getStringWidth(String text) {
/* 177 */     if (text == null)
/* 178 */       return 0; 
/* 180 */     int width = 0;
/* 181 */     CFont.CharData[] currentData = this.charData;
/* 182 */     boolean bold = false;
/* 183 */     boolean italic = false;
/* 184 */     int size = text.length();
/* 185 */     int i = 0;
/* 186 */     while (i < size) {
/* 187 */       char character = text.charAt(i);
/* 188 */       if (character == '§' && i < size) {
/* 189 */         int colorIndex = "0123456789abcdefklmnor".indexOf(character);
/* 190 */         if (colorIndex < 16) {
/* 191 */           bold = false;
/* 192 */           italic = false;
/* 193 */         } else if (colorIndex == 17) {
/* 194 */           bold = true;
/* 195 */           currentData = italic ? this.boldItalicChars : this.boldChars;
/* 196 */         } else if (colorIndex == 20) {
/* 197 */           italic = true;
/* 198 */           currentData = bold ? this.boldItalicChars : this.italicChars;
/* 199 */         } else if (colorIndex == 21) {
/* 200 */           bold = false;
/* 201 */           italic = false;
/* 202 */           currentData = this.charData;
/*     */         } 
/* 204 */         i++;
/* 205 */       } else if (character < currentData.length && character >= '\000') {
/* 206 */         width += (currentData[character]).width - 8 + this.charOffset;
/*     */       } 
/* 208 */       i++;
/*     */     } 
/* 210 */     return width / 2;
/*     */   }
/*     */   
/*     */   public void setFont(Font font) {
/* 215 */     super.setFont(font);
/* 216 */     setupBoldItalicIDs();
/*     */   }
/*     */   
/*     */   public void setAntiAlias(boolean antiAlias) {
/* 221 */     super.setAntiAlias(antiAlias);
/* 222 */     setupBoldItalicIDs();
/*     */   }
/*     */   
/*     */   public void setFractionalMetrics(boolean fractionalMetrics) {
/* 227 */     super.setFractionalMetrics(fractionalMetrics);
/* 228 */     setupBoldItalicIDs();
/*     */   }
/*     */   
/*     */   private void setupBoldItalicIDs() {
/* 232 */     this.texBold = setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
/* 233 */     this.texItalic = setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
/*     */   }
/*     */   
/*     */   private void drawLine(double x, double y, double x1, double y1, float width) {
/* 237 */     GL11.glDisable(3553);
/* 238 */     GL11.glLineWidth(width);
/* 239 */     GL11.glBegin(1);
/* 240 */     GL11.glVertex2d(x, y);
/* 241 */     GL11.glVertex2d(x1, y1);
/* 242 */     GL11.glEnd();
/* 243 */     GL11.glEnable(3553);
/*     */   }
/*     */   
/*     */   public List<String> wrapWords(String text, double width) {
/* 247 */     ArrayList<String> finalWords = new ArrayList<>();
/* 248 */     if (getStringWidth(text) > width) {
/* 249 */       String[] words = text.split(" ");
/* 250 */       String currentWord = "";
/* 251 */       String[] arrstring = words;
/* 252 */       int n = arrstring.length;
/* 253 */       int n2 = 0;
/* 254 */       while (n2 < n) {
/* 255 */         String word = arrstring[n2];
/* 256 */         if (getStringWidth(String.valueOf(currentWord) + word + " ") < width) {
/* 257 */           currentWord = String.valueOf(currentWord) + word + " ";
/*     */         } else {
/* 259 */           finalWords.add(currentWord);
/* 260 */           currentWord = word + " ";
/*     */         } 
/* 262 */         n2++;
/*     */       } 
/* 264 */       if (currentWord.length() > 0)
/* 265 */         if (getStringWidth(currentWord) < width) {
/* 266 */           finalWords.add(currentWord + " ");
/* 267 */           currentWord = "";
/*     */         } else {
/* 269 */           for (String s : formatString(currentWord, width))
/* 270 */             finalWords.add(s); 
/*     */         }  
/*     */     } else {
/* 275 */       finalWords.add(text);
/*     */     } 
/* 277 */     return finalWords;
/*     */   }
/*     */   
/*     */   public List<String> formatString(String string, double width) {
/* 281 */     ArrayList<String> finalWords = new ArrayList<>();
/* 282 */     String currentWord = "";
/* 283 */     int lastColorCode = 65535;
/* 284 */     char[] chars = string.toCharArray();
/* 285 */     int i = 0;
/* 286 */     while (i < chars.length) {
/* 287 */       char c = chars[i];
/* 288 */       if (c == '§' && i < chars.length - 1)
/* 289 */         lastColorCode = chars[i + 1]; 
/* 291 */       if (getStringWidth(String.valueOf(currentWord) + c) < width) {
/* 292 */         currentWord = String.valueOf(currentWord) + c;
/*     */       } else {
/* 294 */         finalWords.add(currentWord);
/* 295 */         currentWord = String.valueOf(167 + lastColorCode) + String.valueOf(c);
/*     */       } 
/* 297 */       i++;
/*     */     } 
/* 299 */     if (currentWord.length() > 0)
/* 300 */       finalWords.add(currentWord); 
/* 302 */     return finalWords;
/*     */   }
/*     */   
/*     */   private void setupMinecraftColorcodes() {
/* 306 */     int index = 0;
/* 307 */     while (index < 32) {
/* 308 */       int noClue = (index >> 3 & 0x1) * 85;
/* 309 */       int red = (index >> 2 & 0x1) * 170 + noClue;
/* 310 */       int green = (index >> 1 & 0x1) * 170 + noClue;
/* 311 */       int blue = (index >> 0 & 0x1) * 170 + noClue;
/* 312 */       if (index == 6)
/* 313 */         red += 85; 
/* 315 */       if (index >= 16) {
/* 316 */         red /= 4;
/* 317 */         green /= 4;
/* 318 */         blue /= 4;
/*     */       } 
/* 320 */       this.colorCode[index] = (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;
/* 321 */       index++;
/*     */     } 
/*     */   }
/*     */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */