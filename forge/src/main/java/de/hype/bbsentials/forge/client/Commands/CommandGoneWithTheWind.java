package de.hype.bbsentials.forge.client.Commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import static de.hype.bbsentials.forge.client.BBsentials.bbserver;


public class CommandGoneWithTheWind extends CommandBase {

    @Override
    public String getCommandName() {
        return "gonewiththewind";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/gonewiththewind";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        bbserver.sendMessage("?dwevent gonewiththewind");
    }
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}