/*    */ package me.azazeldev.gndfiles.gui;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ 
/*    */ public class Command extends CommandBase {
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 14 */     return true;
/*    */   }
/*    */   
/*    */   public List<String> func_71514_a() {
/* 19 */     return Arrays.asList("gnd", "gui");
/*    */   }
/*    */   
/*    */   public String func_71517_b() {
/* 24 */     return "guinodes";
/*    */   }
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 29 */     return "/guinodes";
/*    */   }
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) {
/* 34 */     new DelayedTask(() -> Minecraft.func_71410_x().func_147108_a(new GUI()), 6);
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */