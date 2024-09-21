package me.azazeldev.gndfiles.gndmain;

import java.util.Map;

import me.azazeldev.gndfiles.gndmain.types.Node;
import me.azazeldev.gndfiles.gndmain.types.Scrollable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class Drawer {
    public static void draw(Node n, Minecraft mc, ScaledResolution sr, int scrollProgress) {
        if (n instanceof Scrollable) {
            GL11.glEnable(3089);
            int scaleFactor = sr.getScaleFactor();
            GL11.glScissor((int)(n.x * scaleFactor), (int)(mc.displayHeight - (n.y + n.height) * scaleFactor), (int)(n.width * scaleFactor), (int)(n.height * scaleFactor));
        }
        if (n.radius >= 100) {
            RenderUtils.drawCircle(n.x, n.y, (n.width + n.height) / 2.0F, n.color);
        } else {
            RenderUtils.drawRoundedRect(n.x + (n instanceof Scrollable ? ((Scrollable) n).scrollDir == 1 ? scrollProgress : 0 : 0), n.y + (n instanceof Scrollable ? ((Scrollable) n).scrollDir == 0 ? scrollProgress : 0 : 0), n.x + n.width, n.y + n.height + (n instanceof Scrollable ? scrollProgress : 0), n.radius, n.color);
        }

        if (!n.children.isEmpty())
            for (Node c : n.children)
                draw(c, mc, sr, scrollProgress);
        if (n instanceof Scrollable)
            GL11.glDisable(3089);
    }

    public static void draw(Map<String, Node> nodeMap, Minecraft mc, ScaledResolution sr, int scrollProgress) {
        for (Node n : nodeMap.values())
            draw(n, mc, sr, scrollProgress);
    }
}