/*    */ package me.azazeldev.coinui.utility;
/*    */ 
/*    */ public class MainUtils {
/*    */   public static String formatNumber(double number) {
/*  6 */     if (number < 1000.0D)
/*  7 */       return String.valueOf(number); 
/*  8 */     if (number < 1000000.0D)
/*  9 */       return String.format("%.1fk", number / 1000.0D); 
/* 11 */     return String.format("%.1fM", number / 1000000.0D);
/*    */   }
/*    */ }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */