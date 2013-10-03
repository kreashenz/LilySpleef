package com.strayyakits.kreashenz.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.strayyakits.kreashenz.Lilypad;

public class CmdMain implements CommandExecutor {

	private Lilypad plugin;

	public CmdMain(Lilypad plugin){
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("lilypad")){
			if(s instanceof Player){
				Player p = (Player)s;
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("leave")){
						if(p.hasPermission("lilypad.leave")){
							
						}
					}
				}
			}
		}
		return true;
	}

}
