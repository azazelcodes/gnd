/*    */ package me.azazeldev.coinui.commands;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import me.azazeldev.coinui.commands.sub.Subcommand;
/*    */ import me.azazeldev.coinui.gui.GUI;
/*    */ import me.azazeldev.coinui.utility.DelayedTask;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ 
/*    */ public class CoinUICommand extends CommandBase {
/*    */   private final Subcommand[] subcommands;
/*    */   
/*    */   public CoinUICommand(Subcommand[] subcommands) {
/* 23 */     this.subcommands = subcommands;
/*    */   }
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 28 */     return true;
/*    */   }
/*    */   
/*    */   public List<String> func_71514_a() {
/* 33 */     return Arrays.asList("cui", "coinu");
/*    */   }
/*    */   
/*    */   public String func_71517_b() {
/* 38 */     return "coinui";
/*    */   }
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 43 */     return "/coinui <subcommand> <arguments>";
/*    */   }
/*    */   
/*    */   public void sendHelp(ICommandSender sender) {
/* 47 */     List<String> commandUsages = new LinkedList<>();
/* 48 */     for (Subcommand subcommand : this.subcommands) {
/* 49 */       if (!subcommand.isHidden())
/* 50 */         commandUsages.add(EnumChatFormatting.GRAY + "/coinui " + subcommand.getCommandName() + " " + 
/* 51 */             subcommand.getCommandUsage() + EnumChatFormatting.DARK_GRAY + " - " + EnumChatFormatting.GOLD + subcommand.getCommandDescription()); 
/*    */     } 
/* 54 */     sender.func_145747_a(new ChatComponentText(EnumChatFormatting.GOLD + "CoinUI " + EnumChatFormatting.GREEN + "v@version@" + "\n" + 
/* 55 */           String.join("\n", commandUsages)));
/*    */   }
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) {
/* 60 */     if (args.length == 0) {
/* 61 */       new DelayedTask(() -> Minecraft.func_71410_x().func_147108_a(new GUI()), 6);
/*    */       return;
/*    */     } 
/* 64 */     for (Subcommand subcommand : this.subcommands) {
/* 65 */       if (Objects.equals(args[0], subcommand.getCommandName())) {
/* 66 */         if (!subcommand.processCommand(sender, Arrays.<String>copyOfRange(args, 1, args.length)));
/*    */         return;
/*    */       } 
/*    */     } 
/* 72 */     sendHelp(sender);
/*    */   }
/*    */   
/*    */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/* 77 */     List<String> possibilities = new LinkedList<>();
/* 78 */     for (Subcommand subcommand : this.subcommands)
/* 79 */       possibilities.add(subcommand.getCommandName()); 
/* 81 */     if (args.length == 1)
/* 82 */       return func_175762_a(args, possibilities); 
/* 84 */     return null;
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */