package me.azazeldev.gndfiles.gui;

import java.io.IOException;

import me.azazeldev.gndfiles.Main;
import me.azazeldev.gndfiles.gndmain.Drawer;
import me.azazeldev.gndfiles.gndmain.Parser;
import me.azazeldev.gndfiles.gndmain.RenderUtils;
import me.azazeldev.gndfiles.gndmain.types.Clickable;
import me.azazeldev.gndfiles.gndmain.types.Node;
import me.azazeldev.gndfiles.gndmain.types.Scrollable;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;

public class GUI extends GuiScreen {
    public GUI() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    public void initGui() {
        super.initGui();
        try {
            RenderUtils.initAA();
        } catch (LWJGLException e) {
            Main.l.error("Error initializing Anti-Aliasing, continuing...");
        }
        Main.l.info("Init");


        if (Main.test) {
            try {
                Main.nodeMap = Parser.parseFile(MConfig.mconfig);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        drawWorldBackground(0);
        ScaledResolution sr = new ScaledResolution(this.mc);
        Parser.setVariable("mouseX", String.valueOf(mouseX));
        Parser.setVariable("mouseY", String.valueOf(mouseY));
        Parser.reparseVariables();
        Drawer.draw(Main.nodeMap, this.mc, sr);
    }

    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        for (Node a : Drawer.actionables) {
            if (a.intersects(Mouse.getX(), Mouse.getY())) {
                if (a instanceof Scrollable) {
                    int wheelState = Mouse.getEventDWheel();
                    if (wheelState != 0)
                        ((Scrollable) a).scrollProgress += (wheelState > 0) ? -8 : 8;
                }
                if (a instanceof Clickable)
                    if (Mouse.isButtonDown(1))
                        ((Clickable) a).run();
            }
        }
    }
}