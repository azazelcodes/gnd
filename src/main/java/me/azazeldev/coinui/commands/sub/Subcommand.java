package me.azazeldev.coinui.commands.sub;

import net.minecraft.command.ICommandSender;

public interface Subcommand {
  String getCommandName();
  
  boolean isHidden();
  
  String getCommandUsage();
  
  String getCommandDescription();
  
  boolean processCommand(ICommandSender paramICommandSender, String[] paramArrayOfString);
}


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */