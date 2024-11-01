package me.azazeldev.gndfiles.gui;

import java.io.IOException;

import me.azazeldev.gndfiles.gndmain.NodeScreen;
import net.minecraftforge.common.MinecraftForge;

public class GUI extends NodeScreen {

    public GUI() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    public void initGui() {
        super.initGui(MConfig.mconfig);
    }
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
    }
}