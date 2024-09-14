/*    */ package me.azazeldev.coinui.commands.sub;
/*    */ 
/*    */ import me.azazeldev.coinui.Main;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ 
/*    */ public class Help implements Subcommand {
/*    */   public String getCommandName() {
/*  8 */     return "help";
/*    */   }
/*    */   
/*    */   public boolean isHidden() {
/* 13 */     return false;
/*    */   }
/*    */   
/*    */   public String getCommandUsage() {
/* 18 */     return "";
/*    */   }
/*    */   
/*    */   public String getCommandDescription() {
/* 23 */     return "Sends the help message";
/*    */   }
/*    */   
/*    */   public boolean processCommand(ICommandSender sender, String[] args) {
/* 28 */     Main.commandManager.sendHelp(sender);
/* 29 */     return true;
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */