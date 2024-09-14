/*     */ package me.azazeldev.coinui.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import me.azazeldev.coinui.Main;
/*     */ import me.azazeldev.coinui.gui.font.FontLoaders;
/*     */ import me.azazeldev.coinui.modules.Category;
/*     */ import me.azazeldev.coinui.modules.Module;
/*     */ import me.azazeldev.coinui.modules.Modules;
/*     */ import me.azazeldev.coinui.utility.Element;
/*     */ import me.azazeldev.coinui.utility.RenderUtils;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ public class GUI extends GuiScreen {
/*     */   public GUI() {
/*  29 */     MinecraftForge.EVENT_BUS.register(this);
/*     */   }
/*     */   
/*  31 */   private boolean close = false;
/*     */   
/*     */   private boolean closed;
/*     */   
/*     */   private float dragX;
/*     */   
/*     */   private float dragY;
/*     */   
/*     */   public float lastPercent;
/*     */   
/*     */   public float percent;
/*     */   
/*     */   public float percent2;
/*     */   
/*     */   public float lastPercent2;
/*     */   
/*     */   public float outro;
/*     */   
/*     */   public float lastOutro;
/*     */   
/*  45 */   static float windowX = 180.0F, windowY = 50.0F;
/*     */   
/*  46 */   static float width = 620.0F;
/*     */   
/*  46 */   static float height = 360.0F;
/*     */   
/*     */   int lastTypedKeycode;
/*     */   
/*     */   char lastTypedChar;
/*     */   
/*     */   char emptyChar;
/*     */   
/*     */   public void func_73866_w_() {
/*  57 */     super.func_73866_w_();
/*  58 */     this.percent = 1.33F;
/*  59 */     this.lastPercent = 1.0F;
/*  60 */     this.percent2 = 1.33F;
/*  61 */     this.lastPercent2 = 1.0F;
/*  62 */     this.outro = 1.0F;
/*  63 */     this.lastOutro = 1.0F;
/*     */   }
/*     */   
/*     */   public float smoothTrans(double current, double last) {
/*  67 */     return (float)(current + (last - current) / (Minecraft.func_175610_ah() / 8));
/*     */   }
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/*  73 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*  75 */     func_146270_b(0);
/*  76 */     ScaledResolution sr = new ScaledResolution(this.field_146297_k);
/*  77 */     float outro = smoothTrans(this.outro, this.lastOutro);
/*  79 */     if (this.field_146297_k.field_71441_e != null) {
/*  80 */       if (this.field_146297_k.field_71462_r == null) {
/*  81 */         GlStateManager.func_179109_b((sr.func_78326_a() / 2), (sr.func_78328_b() / 2), 0.0F);
/*  82 */         GlStateManager.func_179152_a(outro, outro, 0.0F);
/*  83 */         GlStateManager.func_179109_b((-sr.func_78326_a() / 2), (-sr.func_78328_b() / 2), 0.0F);
/*     */       } 
/*  87 */       this.percent = smoothTrans(this.percent, this.lastPercent);
/*  88 */       this.percent2 = smoothTrans(this.percent2, this.lastPercent2);
/*  90 */       if (this.percent > 0.98D) {
/*  91 */         GlStateManager.func_179109_b((sr.func_78326_a() / 2), (sr.func_78328_b() / 2), 0.0F);
/*  92 */         GlStateManager.func_179152_a(this.percent, this.percent, 0.0F);
/*  93 */         GlStateManager.func_179109_b((-sr.func_78326_a() / 2), (-sr.func_78328_b() / 2), 0.0F);
/*  94 */       } else if (this.percent2 <= 1.0F) {
/*  95 */         GlStateManager.func_179109_b((sr.func_78326_a() / 2), (sr.func_78328_b() / 2), 0.0F);
/*  96 */         GlStateManager.func_179152_a(this.percent2, this.percent2, 0.0F);
/*  97 */         GlStateManager.func_179109_b((-sr.func_78326_a() / 2), (-sr.func_78328_b() / 2), 0.0F);
/*     */       } 
/* 100 */       if (this.percent <= 1.5D && this.close) {
/* 101 */         this.percent = smoothTrans(this.percent, 2.0D);
/* 102 */         this.percent2 = smoothTrans(this.percent2, 2.0D);
/*     */       } 
/* 105 */       if (this.percent >= 1.4D && this.close) {
/* 106 */         this.percent = 1.5F;
/* 107 */         this.closed = true;
/* 108 */         this.field_146297_k.field_71462_r = null;
/*     */       } 
/*     */     } 
/* 113 */     if (isHovered(windowX, windowY, windowX + width, windowY + 20.0F, mouseX, mouseY) && Mouse.isButtonDown(0)) {
/* 114 */       if (this.dragX == 0.0F && this.dragY == 0.0F) {
/* 115 */         this.dragX = mouseX - windowX;
/* 116 */         this.dragY = mouseY - windowY;
/*     */       } else {
/* 118 */         windowX = mouseX - this.dragX;
/* 119 */         windowY = mouseY - this.dragY;
/*     */       } 
/*     */     } else {
/* 122 */       this.dragX = 0.0F;
/* 123 */       this.dragY = 0.0F;
/*     */     } 
/*     */     try {
/* 127 */       RenderUtils.initAA();
/* 128 */     } catch (LWJGLException e) {
/* 129 */       throw new RuntimeException(e);
/*     */     } 
/* 133 */     RenderUtils.drawRoundedRect(windowX, windowY, windowX + width, windowY + height, 20, Theme.bodyColor.getRGB());
/* 134 */     Gui.func_73734_a((int)windowX, (int)windowY, (int)(windowX + width), (int)(windowY + 20.0F), Theme.topColor.getRGB());
/* 138 */     Gui.func_73734_a((int)windowX + 2, (int)windowY + 48, (int)(windowX + width) - 2, (int)(windowY + 50.0F), Theme.categoryColor.getRGB());
/* 140 */     int cats = 0;
/* 141 */     for (Category cat : Category.values()) {
/* 142 */       RenderUtils.drawRoundedRect(windowX + 30.0F + (cats * 125), windowY + 28.0F, windowX + 30.0F + 100.0F + (cats * 115), windowY + 58.0F, 10, Theme.categoryColor.getRGB());
/* 145 */       if (isHovered(windowX + 32.0F + (cats * 125), windowY + 30.0F, windowX + 28.0F + 100.0F + (cats * 115), windowY + 68.0F, mouseX, mouseY) && Mouse.isButtonDown(0))
/* 146 */         Main.page = cats; 
/* 149 */       Gui.func_73734_a((int)windowX + 20, (int)windowY + 50, (int)(windowX + width - 20.0F), (int)(windowY + 100.0F), Theme.bodyColor.getRGB());
/* 151 */       if (Main.page == cats)
/* 152 */         RenderUtils.drawRoundedRect(windowX + 32.0F + (cats * 125), windowY + 30.0F, windowX + 28.0F + 100.0F + (cats * 115), windowY + 68.0F, 10, Theme.bodyColor.getRGB()); 
/* 155 */       char[] charArray = cat.toString().toLowerCase().toCharArray();
/* 156 */       boolean foundSpace = true;
/* 158 */       for (int i = 0; i < charArray.length; i++) {
/* 159 */         if (Character.isLetter(charArray[i])) {
/* 160 */           if (foundSpace)
/* 160 */             charArray[i] = Character.toUpperCase(charArray[i]); 
/* 161 */           foundSpace = false;
/*     */         } else {
/* 163 */           foundSpace = true;
/*     */         } 
/*     */       } 
/* 166 */       FontLoaders.arial24.drawString(String.valueOf(charArray), windowX + 30.0F + (cats * 125) + (FontLoaders.arial24.getStringWidth(String.valueOf(charArray)) / 2), windowY + 32.0F, Theme.descriptionColor.getRGB());
/* 168 */       cats++;
/*     */     } 
/* 172 */     int mods = 0;
/* 173 */     for (Module module : (Iterable<Module>)Modules.modules) {
/* 174 */       if (Category.valueOf(String.valueOf(module.category)).ordinal() == Main.page) {
/* 175 */         if (module.type.toString() == "TOGGLE") {
/* 176 */           new Element();
/* 176 */           Element.Toggle(windowX, windowY + 78.0F + (64 * mods), module.name, module.description, true, false, mouseX, mouseY, module.internalID);
/*     */         } 
/* 178 */         if (module.type.toString() == "TEXT")
/* 179 */           new Element().TextInput(windowX, windowY + 78.0F + (64 * mods), module.name, module.description, true, false, mouseX, mouseY, module.internalID, this.lastTypedChar, this.lastTypedKeycode, false, new String[] { " ", "\"", "\\" }); 
/* 181 */         if (module.type.toString() == "SLIDER")
/* 182 */           new Element().Slider(windowX, windowY + 78.0F + (64 * mods), module.name, module.description, true, false, mouseX, mouseY, module.internalID); 
/* 184 */         mods++;
/* 185 */         this.lastTypedKeycode = -1;
/* 186 */         this.lastTypedChar = this.emptyChar;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void func_73869_a(char typedChar, int keyCode) {
/* 194 */     this.lastTypedKeycode = keyCode;
/* 195 */     this.lastTypedChar = typedChar;
/* 197 */     if (!this.closed && keyCode == 1) {
/* 198 */       this.close = true;
/* 199 */       this.field_146297_k.field_71417_B.func_74372_a();
/* 200 */       this.field_146297_k.field_71415_G = true;
/*     */       return;
/*     */     } 
/* 205 */     if (this.close) {
/* 206 */       windowX = 400.0F;
/* 207 */       windowY = 205.0F;
/* 208 */       this.field_146297_k.func_147108_a(null);
/*     */     } 
/*     */     try {
/* 212 */       super.func_73869_a(typedChar, keyCode);
/* 213 */     } catch (IOException e) {
/* 214 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void func_146284_a(GuiButton button) {}
/*     */   
/*     */   public static boolean isHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
/* 222 */     return (mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2);
/*     */   }
/*     */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */