package me.azazeldev.gndfiles;

import java.io.IOException;
import java.util.Map;
import me.azazeldev.gndfiles.gndmain.types.Node;
import me.azazeldev.gndfiles.gndmain.Parser;
import me.azazeldev.gndfiles.gui.Command;
import me.azazeldev.gndfiles.gui.MConfig;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = "gndex", useMetadata = true)
public class Main {
    public static Main instance;
    public static Command commandManager = new Command();
    public static Logger l;
    public static Map<String, Node> nodeMap;


    public static boolean test = true;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws IOException {
        ProgressManager.ProgressBar progressBar = ProgressManager.push("gndex", 2);
        progressBar.step("Setting up mod...");
        instance = this;
        l = LogManager.getLogger();
        progressBar.step("Setting up GUI...");
        MConfig.init();
        nodeMap = Parser.parseFile(MConfig.mconfig);
        ClientCommandHandler.instance.registerCommand(commandManager);
        ProgressManager.pop(progressBar);
    }
}