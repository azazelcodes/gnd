package me.azazeldev.gndfiles.gndmain;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

public class RenderUtils {
    public static void glColor(int hex) {
        float alpha = (hex >> 24 & 0xFF) / 255.0F;
        float red = (hex >> 16 & 0xFF) / 255.0F;
        float green = (hex >> 8 & 0xFF) / 255.0F;
        float blue = (hex & 0xFF) / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public static void circle(float x, float y, float radius, int fill) {
        arc(x, y, 0.0F, 360.0F, radius, fill);
    }

    public static void drawCircle(float x, float y, float radius, int color) {
        float alpha = (color >> 24 & 0xFF) / 255.0F;
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glLineWidth(1.0F);
        GL11.glBegin(9);
        for (int i = 0; i <= 360; i++)
            GL11.glVertex2d(x + Math.sin(i * Math.PI / 180.0D) * radius, y + Math.cos(i * Math.PI / 180.0D) * radius);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void arc(float x, float y, float start, float end, float radius, int color) {
        arcEllipse(x, y, start, end, radius, radius, color);
    }

    public static void arcEllipse(float x, float y, float start, float end, float w, float h, int color) {
        GlStateManager.color(0.0F, 0.0F, 0.0F);
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.0F);
        float temp;
        if (start > end) {
            temp = end;
            end = start;
            start = temp;
        }

        float var11 = (color >> 24 & 0xFF) / 255.0F;
        float var12 = (color >> 16 & 0xFF) / 255.0F;
        float var13 = (color >> 8 & 0xFF) / 255.0F;
        float var14 = (color & 0xFF) / 255.0F;
        Tessellator var15 = Tessellator.getInstance();
        var15.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(var12, var13, var14, var11);
        if (var11 > 0.5F) {
            GL11.glEnable(2848);
            GL11.glLineWidth(2.0F);
            GL11.glBegin(3);
            for (float f = end; f >= start; f -= 4.0F) {
                float ldx = (float)Math.cos(f * Math.PI / 180.0D) * w * 1.001F;
                float ldy = (float)Math.sin(f * Math.PI / 180.0D) * h * 1.001F;
                GL11.glVertex2f(x + ldx, y + ldy);
            }
            GL11.glEnd();
            GL11.glDisable(2848);
        }
        GL11.glBegin(6);
        for (float i = end; i >= start; i -= 4.0F) {
            float ldx = (float)Math.cos(i * Math.PI / 180.0D) * w;
            float ldy = (float)Math.sin(i * Math.PI / 180.0D) * h;
            GL11.glVertex2f(x + ldx, y + ldy);
        }
        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawRect(float x1, float y1, float x2, float y2, int color) {
        Gui.drawRect((int)x1, (int)y1, (int)x2, (int)y2, color);
    }

    public static void drawRoundedRect(float x1, float y1, float x2, float y2, int round, int color) {
        if (round == 0) {
            Gui.drawRect((int)x1, (int)y1, (int)x2, (int)y2, color);
            return;
        }
        x1 += (float)((round / 2.0F) + 0.5D);
        y1 += (float)((round / 2.0F) + 0.5D);
        x2 -= (float)((round / 2.0F) + 0.5D);
        y2 -= (float)((round / 2.0F) + 0.5D);
        Gui.drawRect((int)x1, (int)y1, (int)x2, (int)y2, color);
        circle(x2 - round / 2.0F, y1 + round / 2.0F, round, color);
        circle(x1 + round / 2.0F, y2 - round / 2.0F, round, color);
        circle(x1 + round / 2.0F, y1 + round / 2.0F, round, color);
        circle(x2 - round / 2.0F, y2 - round / 2.0F, round, color);
        Gui.drawRect((int)(x1 - round / 2.0F - 0.5F), (int)(y1 + round / 2.0F), (int)x2, (int)(y2 - round / 2.0F), color);
        Gui.drawRect((int)x1, (int)(y1 + round / 2.0F), (int)(x2 + round / 2.0F + 0.5F), (int)(y2 - round / 2.0F), color);
        Gui.drawRect((int)(x1 + round / 2.0F), (int)(y1 - round / 2.0F - 0.5F), (int)(x2 - round / 2.0F), (int)(y2 - round / 2.0F), color);
        Gui.drawRect((int)(x1 + round / 2.0F), (int)y1, (int)(x2 - round / 2.0F), (int)(y2 + round / 2.0F + 0.5F), color);
    }

    public static void initAA() throws LWJGLException {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
    }
}