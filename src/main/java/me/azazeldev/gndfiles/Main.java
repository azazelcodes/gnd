package me.azazeldev.gndfiles;

import java.io.IOException;
import java.util.Map;
import me.azazeldev.gndfiles.gndmain.types.Node;
import me.azazeldev.gndfiles.gndmain.Parser;
import me.azazeldev.gndfiles.gui.Command;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static Main instance;
    public static Command commandManager = new Command();
    public static Logger l;
    public static Map<String, Node> nodeMap;

    @net.minecraftforge.fml.common.Mod.EventHandler
    public void init(FMLInitializationEvent event) throws IOException {
        ProgressManager.ProgressBar progressBar = ProgressManager.push("CoinUI", 2);
        instance = this;
        l = LogManager.getLogger();
        nodeMap = Parser.parseFile("test.gnd");
        ClientCommandHandler.instance.registerCommand(commandManager);
        progressBar.step("Initializing GUI");
        ProgressManager.pop(progressBar);
    }
}