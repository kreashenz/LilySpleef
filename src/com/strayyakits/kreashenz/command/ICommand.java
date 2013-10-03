package com.strayyakits.kreashenz.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.strayyakits.kreashenz.Lilypad;

public abstract class ICommand {

	private Lilypad plugin;

	protected ICommand(Lilypad plugin){
		this.plugin = plugin;
	}

	public abstract void execute(CommandSender s, Command cmd, String[] args);

}
