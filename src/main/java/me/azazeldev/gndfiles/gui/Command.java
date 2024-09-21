package me.azazeldev.gndfiles.gui;

import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class Command extends CommandBase {
  public boolean canCommandSenderUseCommand(ICommandSender sender) {
      return true;
  }
  
  public List<String> getCommandAliases() {
      return Arrays.asList("gnd", "gui");
  }
  
  public String getCommandName() {
      return "guinodes";
  }
  
  public String getCommandUsage(ICommandSender sender) {
      return "/guinodes";
  }
  
  public void processCommand(ICommandSender sender, String[] args) {
      new DelayedTask(() -> Minecraft.getMinecraft().displayGuiScreen(new GUI()), 6);
  }
}