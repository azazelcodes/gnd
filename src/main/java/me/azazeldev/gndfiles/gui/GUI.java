/*    */ package me.azazeldev.gndfiles.gui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import me.azazeldev.gndfiles.Main;
/*    */ import me.azazeldev.gndfiles.gndmain.Drawer;
/*    */ import me.azazeldev.gndfiles.gndmain.Parser;
/*    */ import me.azazeldev.gndfiles.gndmain.RenderUtils;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import org.lwjgl.LWJGLException;
/*    */ import org.lwjgl.input.Mouse;
/*    */ 
/*    */ public class GUI extends GuiScreen {
/*    */   public int scrollVar;
/*    */   
/*    */   public GUI() {
/* 18 */     MinecraftForge.EVENT_BUS.register(this);
/*    */   }
/*    */   
/*    */   public void func_73866_w_() {
/* 26 */     super.func_73866_w_();
/*    */     try {
/* 28 */       RenderUtils.initAA();
/* 29 */     } catch (LWJGLException e) {
/* 30 */       Main.l.error("Error initializing Anti-Aliasing, continuing...");
/*    */     } 
/* 32 */     Main.l.info("Init");
/*    */   }
/*    */   
/*    */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/* 38 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/* 40 */     func_146270_b(0);
/* 41 */     ScaledResolution sr = new ScaledResolution(this.field_146297_k);
/* 43 */     Parser.setVariable("mouseX", String.valueOf(mouseX));
/* 44 */     Parser.setVariable("mouseY", String.valueOf(mouseY));
/* 45 */     Parser.reparseVariables();
/* 46 */     Drawer.draw(Main.nodeMap, this.field_146297_k, sr, this.scrollVar);
/*    */   }
/*    */   
/*    */   public void func_146274_d() throws IOException {
/* 52 */     super.func_146274_d();
/* 53 */     int wheelState = Mouse.getEventDWheel();
/* 54 */     if (wheelState != 0)
/* 55 */       this.scrollVar += (wheelState > 0) ? -8 : 8; 
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */