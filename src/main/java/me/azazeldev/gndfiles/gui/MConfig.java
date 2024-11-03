package me.azazeldev.gndfiles.gui;

import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;

public class MConfig {

    public static File mconfig;
    public static String mpath;

    public static void init() throws IOException {
        mpath = Minecraft.getMinecraft().mcDataDir.getAbsolutePath() + "//gndex";
        mconfig = new File(mpath + "//main.gnd");
        if (!mconfig.exists() || mconfig.isDirectory()) {
            mconfig.getParentFile().mkdirs();
            mconfig.createNewFile();
        }
    }
}
