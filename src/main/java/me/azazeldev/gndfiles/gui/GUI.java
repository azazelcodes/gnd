package me.azazeldev.gndfiles.gui;

import java.io.IOException;
import me.azazeldev.gndfiles.Main;
import me.azazeldev.gndfiles.gndmain.Drawer;
import me.azazeldev.gndfiles.gndmain.Parser;
import me.azazeldev.gndfiles.gndmain.RenderUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;

public class GUI extends GuiScreen {
    public int scrollVar;

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
    }
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        drawWorldBackground(0);
        ScaledResolution sr = new ScaledResolution(this.mc);
        Parser.setVariable("mouseX", String.valueOf(mouseX));
        Parser.setVariable("mouseY", String.valueOf(mouseY));
        Parser.reparseVariables();
        Drawer.draw(Main.nodeMap, this.mc, sr, this.scrollVar);
    }

    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int wheelState = Mouse.getEventDWheel();
        if (wheelState != 0)
            this.scrollVar += (wheelState > 0) ? -8 : 8;
    }
}