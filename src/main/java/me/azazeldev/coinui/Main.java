/*    */ package me.azazeldev.coinui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import me.azazeldev.coinui.commands.CoinUICommand;
/*    */ import me.azazeldev.coinui.commands.sub.Help;
/*    */ import me.azazeldev.coinui.commands.sub.Subcommand;
/*    */ import me.azazeldev.coinui.modules.Modules;
/*    */ import me.azazeldev.coinui.utility.Config;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraftforge.client.ClientCommandHandler;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventHandler;
/*    */ import net.minecraftforge.fml.common.ProgressManager;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ 
/*    */ @Mod(modid = "coinui", name = "CoinUI - UI Mod", version = "v@version@", acceptedMinecraftVersions = "[1.8.9]")
/*    */ public class Main {
/*    */   public static String uuid;
/*    */   
/*    */   public static Main instance;
/*    */   
/* 22 */   public static CoinUICommand commandManager = new CoinUICommand(new Subcommand[] { new Help() });
/*    */   
/*    */   public static boolean justClicked;
/*    */   
/* 26 */   public static int page = 0;
/*    */   
/*    */   @Mod.EventHandler
/*    */   public void init(FMLInitializationEvent event) throws IOException {
/* 30 */     ProgressManager.ProgressBar progressBar = ProgressManager.push("CoinUI", 2);
/* 31 */     progressBar.step("Registering events and commands");
/* 32 */     Config.init();
/* 33 */     instance = this;
/* 34 */     System.out.println(instance);
/* 35 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandManager);
/* 36 */     uuid = Minecraft.func_71410_x().func_110432_I().func_148255_b();
/* 37 */     Reference.logger.info("Registered events and commands!");
/* 38 */     progressBar.step("Initializing GUI");
/* 39 */     Modules.init();
/* 40 */     Reference.logger.info("Initialized GUI!");
/* 41 */     ProgressManager.pop(progressBar);
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */