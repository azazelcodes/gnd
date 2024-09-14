/*    */ package me.azazeldev.coinui.modules;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import me.azazeldev.coinui.utility.Config;
/*    */ 
/*    */ public class Module {
/*    */   public String name;
/*    */   
/*    */   public String description;
/*    */   
/*    */   public Category category;
/*    */   
/*    */   public Type type;
/*    */   
/*    */   public String internalID;
/*    */   
/*    */   public JsonElement defaultValue;
/*    */   
/*    */   public Module(String name, String description, Category category, Type type, String internalID, JsonElement defaultValue) {
/* 17 */     this.name = name;
/* 18 */     this.description = description;
/* 19 */     this.category = category;
/* 20 */     this.type = type;
/* 21 */     this.internalID = internalID;
/* 22 */     this.defaultValue = defaultValue;
/* 24 */     if (!Config.getConfig().has(internalID))
/* 25 */       Config.write(internalID, defaultValue); 
/*    */   }
/*    */   
/*    */   public JsonElement getDefaultValue() {
/* 30 */     return this.defaultValue;
/*    */   }
/*    */   
/*    */   public Type getType() {
/* 34 */     return this.type;
/*    */   }
/*    */   
/*    */   public String getInternalID() {
/* 38 */     return this.internalID;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 42 */     return this.name;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 46 */     return this.description;
/*    */   }
/*    */   
/*    */   public Category getCategory() {
/* 50 */     return this.category;
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */