/*    */ package me.azazeldev.coinui.utility;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class APIHandler {
/*    */   public static boolean electionWarn() {
/* 13 */     JsonObject mayor = Objects.<JsonElement>requireNonNull(MainUtils.getJson("https://api.hypixel.net/resources/skyblock/election")).getAsJsonObject().get("mayor").getAsJsonObject().get("name").getAsJsonObject();
/* 14 */     if (((mayor.getAsString() != null) ? true : false) & ((mayor.getAsString() == "Derpy") ? true : false))
/* 15 */       return false; 
/* 17 */     return true;
/*    */   }
/*    */   
/*    */   public static void sendObjectEntries(JsonObject object) {
/* 22 */     for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
/* 23 */       JsonObject current = (JsonObject)object.get(entry.getKey());
/* 24 */       System.out.println(current.toString());
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void pullBZ() {
/* 29 */     JsonObject apiBZObject = Objects.<JsonElement>requireNonNull(MainUtils.getJson("https://rayorsomething.github.io/bz.json")).getAsJsonObject();
/* 30 */     JsonObject apiItemObject = Objects.<JsonElement>requireNonNull(MainUtils.getJson("https://rayorsomething.github.io/items.json")).getAsJsonObject();
/* 32 */     for (Map.Entry<String, JsonElement> keyBZ : apiBZObject.entrySet()) {
/* 33 */       JsonObject bzCurrent = keyBZ.getValue().getAsJsonObject();
/* 34 */       for (Map.Entry<String, JsonElement> key : apiItemObject.entrySet()) {
/* 35 */         JsonObject itemCurrent = key.getValue().getAsJsonObject();
/* 36 */         if (itemCurrent.get("hyid").equals(bzCurrent.get("hyid"))) {
/* 37 */           System.out.println("MATCHED: " + bzCurrent.get("hyid") + " for item " + itemCurrent.get("item"));
/*    */           continue;
/*    */         } 
/* 39 */         System.out.println("COULDNT MATCH: " + bzCurrent.get("hyid"));
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void pullNPC() {
/* 46 */     JsonObject apiNPCObject = Objects.<JsonElement>requireNonNull(MainUtils.getJson("https://rayorsomething.github.io/npc.json")).getAsJsonObject();
/* 47 */     JsonObject apiItemObject = Objects.<JsonElement>requireNonNull(MainUtils.getJson("https://rayorsomething.github.io/items.json")).getAsJsonObject();
/* 49 */     for (Map.Entry<String, JsonElement> keyNPC : apiNPCObject.entrySet()) {
/* 50 */       JsonObject npcCurrent = keyNPC.getValue().getAsJsonObject();
/* 51 */       for (Map.Entry<String, JsonElement> key : apiItemObject.entrySet()) {
/* 52 */         JsonObject itemCurrent = key.getValue().getAsJsonObject();
/* 53 */         if (itemCurrent.get("hyid").equals(npcCurrent.get("hyid")))
/* 54 */           System.out.println("MATCHED: " + npcCurrent.get("hyid") + " for item " + itemCurrent.get("item")); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void pullAH() {
/* 61 */     JsonObject apiAHObject = Objects.<JsonElement>requireNonNull(MainUtils.getJson("https://rayorsomething.github.io/ah.json")).getAsJsonObject();
/* 63 */     for (Map.Entry<String, JsonElement> entry : apiAHObject.entrySet());
/* 66 */     sendObjectEntries(apiAHObject);
/*    */   }
/*    */   
/*    */   public static void getHandData() {
/* 70 */     ItemStack item = (Minecraft.func_71410_x()).field_71439_g.func_71045_bC();
/* 71 */     if (item != null) {
/* 72 */       System.out.println(item.func_111283_C());
/* 73 */       System.out.println(item.func_82840_a((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g, true));
/* 74 */       System.out.println(item.func_77960_j());
/* 75 */       System.out.println(item.func_77978_p());
/*    */     } 
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */