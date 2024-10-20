package me.azazeldev.gndfiles.gndmain;

import java.util.*;

import me.azazeldev.gndfiles.Main;
import me.azazeldev.gndfiles.gndmain.types.Clickable;
import me.azazeldev.gndfiles.gndmain.types.Node;
import me.azazeldev.gndfiles.gndmain.types.Scrollable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class Drawer {
    public static List<Node> actionables = new ArrayList<>();
    public static void draw(Node n, Minecraft mc, ScaledResolution sr) {
        // Create a List of all actionables to check for clicking them
        if (n instanceof Clickable || n instanceof Scrollable) actionables.add(n);

        if (n instanceof Scrollable) {
            GL11.glEnable(3089);
            int scaleFactor = sr.getScaleFactor();
            GL11.glScissor((int)(n.x * scaleFactor), (int)(mc.displayHeight - (n.y + n.height) * scaleFactor), (int)(n.width * scaleFactor), (int)(n.height * scaleFactor));
        }
        if (n.radius >= 100) {
            RenderUtils.drawCircle(n.x, n.y, (n.width + n.height) / 2.0F, n.color);
        } else {
            // this is messy but a oneliner. if needed separate into a scrollProgress local var
            RenderUtils.drawRoundedRect(n.x + (n instanceof Scrollable ? ((Scrollable) n).scrollDir == 1 ? ((Scrollable) n).scrollProgress : 0 : 0), n.y + (n instanceof Scrollable ? ((Scrollable) n).scrollDir == 0 ? ((Scrollable) n).scrollProgress : 0 : 0), n.x + n.width + (n instanceof Scrollable ? ((Scrollable) n).scrollDir == 1 ? ((Scrollable) n).scrollProgress : 0 : 0), n.y + n.height + (n instanceof Scrollable ? ((Scrollable) n).scrollDir == 0 ? ((Scrollable) n).scrollProgress : 0 : 0), n.radius, n.color);
        }

        if (!n.children.isEmpty())
            for (Node c : n.children)
                draw(c, mc, sr);
        if (n instanceof Scrollable)
            GL11.glDisable(3089);
    }

    public static void draw(Map<String, Node> nodeMap, Minecraft mc, ScaledResolution sr) {
        if (!actionables.isEmpty()) {
            actionables.clear();
        }
        for (Node n : nodeMap.values()) {
            draw(n, mc, sr);
        }
    }
}