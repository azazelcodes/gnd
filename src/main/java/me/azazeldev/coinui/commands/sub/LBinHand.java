/*    */ package me.azazeldev.coinui.commands.sub;
/*    */ 
/*    */ import me.azazeldev.coinui.utility.APIHandler;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ 
/*    */ public class LBinHand implements Subcommand {
/*    */   public String getCommandName() {
/* 10 */     return "handbin";
/*    */   }
/*    */   
/*    */   public boolean isHidden() {
/* 15 */     return false;
/*    */   }
/*    */   
/*    */   public String getCommandUsage() {
/* 20 */     return "";
/*    */   }
/*    */   
/*    */   public String getCommandDescription() {
/* 25 */     return "Sends the lowest bin of the item in your hand";
/*    */   }
/*    */   
/*    */   public boolean processCommand(ICommandSender sender, String[] args) {
/* 30 */     APIHandler.getHandData();
/* 31 */     return true;
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */