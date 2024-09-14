/*    */ package me.azazeldev.coinui.utility;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.io.Writer;
/*    */ import me.azazeldev.coinui.modules.Module;
/*    */ import me.azazeldev.coinui.modules.Modules;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import org.apache.commons.io.IOUtils;
/*    */ 
/*    */ public class Config {
/*    */   private static File configFile;
/*    */   
/*    */   private static JsonObject json;
/*    */   
/* 29 */   public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
/*    */   
/*    */   public static void init() throws IOException {
/* 32 */     configFile = new File((Minecraft.func_71410_x()).field_71412_D.getAbsolutePath() + "//coinui//config.json");
/* 33 */     if (configFile.exists() && !configFile.isDirectory()) {
/* 34 */       InputStream is = new FileInputStream(configFile);
/* 35 */       String jsonTxt = IOUtils.toString(is, "UTF-8");
/* 36 */       json = new JsonParser().parse(jsonTxt).getAsJsonObject();
/*    */     } else {
/* 38 */       configFile.getParentFile().mkdirs();
/* 39 */       configFile.createNewFile();
/* 40 */       try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(configFile), "utf-8"))) {
/* 42 */         json = new JsonObject();
/* 43 */         writer1.write(json.toString());
/*    */       } 
/*    */     } 
/* 47 */     try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(configFile), "utf-8"))) {
/* 48 */       for (Module m : Modules.modules) {
/* 49 */         if (!json.has(m.getInternalID()))
/* 50 */           json.add(m.getInternalID(), m.getDefaultValue()); 
/*    */       } 
/* 53 */       writer.write(json.toString());
/*    */     } 
/*    */   }
/*    */   
/*    */   public static JsonObject getConfig() {
/* 59 */     return json;
/*    */   }
/*    */   
/*    */   public static void write(String string, JsonElement jsonTree) {
/* 63 */     JsonObject config = getConfig();
/* 64 */     config.add(string, jsonTree);
/* 65 */     try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(configFile), "utf-8"))) {
/* 66 */       writer.write(config.toString());
/* 68 */     } catch (Exception e) {
/* 69 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */