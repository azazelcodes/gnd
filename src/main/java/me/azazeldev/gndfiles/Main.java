/*    */ package me.azazeldev.gndfiles;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Map;
/*    */ import me.azazeldev.gndfiles.gndmain.Node;
/*    */ import me.azazeldev.gndfiles.gndmain.Parser;
/*    */ import me.azazeldev.gndfiles.gui.Command;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraftforge.client.ClientCommandHandler;
/*    */ import net.minecraftforge.fml.common.Mod.EventHandler;
/*    */ import net.minecraftforge.fml.common.ProgressManager;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class Main {
/*    */   public static Main instance;
/*    */   
/* 18 */   public static Command commandManager = new Command();
/*    */   
/*    */   public static Logger l;
/*    */   
/*    */   public static Map<String, Node> nodeMap;
/*    */   
/*    */   @net.minecraftforge.fml.common.Mod.EventHandler
/*    */   public void init(FMLInitializationEvent event) throws IOException {
/* 23 */     ProgressManager.ProgressBar progressBar = ProgressManager.push("CoinUI", 2);
/* 24 */     progressBar.step("Registering events and commands");
/* 25 */     instance = this;
/* 26 */     l = LogManager.getLogger();
/* 27 */     nodeMap = Parser.parseFile("test.gnd");
/* 28 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandManager);
/* 29 */     progressBar.step("Initializing GUI");
/* 30 */     ProgressManager.pop(progressBar);
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */