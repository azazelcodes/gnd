package me.azazeldev.gndfiles.gndmain;

import java.util.*;

import me.azazeldev.gndfiles.Main;
import me.azazeldev.gndfiles.gndmain.types.Instance;
import me.azazeldev.gndfiles.gndmain.types.Node;
import me.azazeldev.gndfiles.gndmain.types.Scrollable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class Drawer {
    public static void draw(Node n, Minecraft mc, ScaledResolution sr) {
        if (Main.test) Main.l.info("Self: " + n.toString()); Main.l.info("Kids: " + n.children);

        if (n instanceof Scrollable) {
            GL11.glEnable(3089);
            int scaleFactor = sr.getScaleFactor();
            GL11.glScissor((int)(n.x * scaleFactor), (int)(mc.displayHeight - (n.y + n.height) * scaleFactor), (int)(n.width * scaleFactor), (int)(n.height * scaleFactor));
        }
        if (n.radius >= 100) {
            RenderUtils.drawCircle(n.x, n.y, (n.width + n.height) / 2.0F, n.color);
        } else {
            // this is messy but oneliner. if needed separate into a scrollProgress local var
            RenderUtils.drawRoundedRect(
                    n.x + (n instanceof Scrollable && ((Scrollable) n).scrollDir == 1 ? ((Scrollable) n).scrollProgress : 0) + (n.isChild() ? n.parent.x + (n.parent instanceof Scrollable && ((Scrollable) n.parent).scrollDir == 1 ? ((Scrollable) n.parent).scrollProgress : 0) : 0), // x + scrollProgress + parentX + parentScrollProgress
                    n.y + (n instanceof Scrollable && ((Scrollable) n).scrollDir == 0 ? ((Scrollable) n).scrollProgress : 0) + (n.isChild() ? n.parent.y + (n.parent instanceof Scrollable && ((Scrollable) n.parent).scrollDir == 0 ? ((Scrollable) n.parent).scrollProgress : 0) : 0), // ^ same but y
                    n.x + n.width + (n instanceof Scrollable && ((Scrollable) n).scrollDir == 1 ? ((Scrollable) n).scrollProgress : 0) + (n.isChild() ? n.parent.x + (n.parent instanceof Scrollable && ((Scrollable) n.parent).scrollDir == 1 ? ((Scrollable) n.parent).scrollProgress : 0) : 0),  // x + width + scrollProgress + parentX + parentScrollProgress
                    n.y + n.height + (n instanceof Scrollable && ((Scrollable) n).scrollDir == 0 ? ((Scrollable) n).scrollProgress : 0) + (n.isChild() ? n.parent.y + (n.parent instanceof Scrollable && ((Scrollable) n.parent).scrollDir == 0 ? ((Scrollable) n.parent).scrollProgress : 0) : 0), // ^ same but y & height
                    n.radius, n.color);
        }

        if (n instanceof Instance)
            draw(((Instance) n).map, mc, sr);
        if (!n.children.isEmpty())
            for (Node c : n.children)
                draw(c, mc, sr);
        if (n instanceof Scrollable)
            GL11.glDisable(3089);
    }

    public static void draw(Map<String, Node> nodeMap, Minecraft mc, ScaledResolution sr) {
        if (Main.test) Main.l.info("Pushed Map: " + nodeMap);
        for (Node n : nodeMap.values()) {
            draw(n, mc, sr);
        }
    }
}