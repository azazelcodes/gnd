/*    */ package me.azazeldev.coinui.modules;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.ArrayList;
/*    */ import me.azazeldev.coinui.utility.Config;
/*    */ 
/*    */ public class Modules {
/* 11 */   public static ArrayList<Module> modules = new ArrayList<>();
/*    */   
/*    */   public static void init() {
/* 14 */     modules.add(new Module("Toggle", "I love booleans! This is a placeholder description to test the new GUI System.", Category.GENERAL, Type.TOGGLE, "flipper", 
/* 15 */           Config.gson.toJsonTree(false)));
/* 16 */     modules.add(new Module("String", "I love strings! This is a placeholder description to test the new GUI System.", Category.PREMIUM, Type.TEXT, "webhook", 
/* 17 */           Config.gson.toJsonTree("")));
/* 18 */     modules.add(new Module("Slider", "I love sliders! This is a placeholder description to test the new GUI System.", Category.GENERAL, Type.SLIDER, "minprofit", 
/* 19 */           Config.gson.toJsonTree(new Gson().fromJson("{\"value\":1,\"min\":1,\"max\":5,\"step\":1}", JsonObject.class))));
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */