/*    */ package me.azazeldev.gndfiles.gui;
/*    */ 
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ 
/*    */ public class DelayedTask {
/*    */   private int counter;
/*    */   
/*    */   private final Runnable runnable;
/*    */   
/*    */   public DelayedTask(Runnable run, int ticks) {
/* 13 */     this.counter = ticks;
/* 14 */     this.runnable = run;
/* 15 */     MinecraftForge.EVENT_BUS.register(this);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onTick(TickEvent.ClientTickEvent event) {
/* 20 */     if (event.phase != TickEvent.Phase.START)
/*    */       return; 
/* 22 */     if (this.counter <= 0) {
/* 23 */       MinecraftForge.EVENT_BUS.unregister(this);
/* 24 */       this.runnable.run();
/*    */     } 
/* 27 */     this.counter--;
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */