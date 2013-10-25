package com.strayyakits.kreashenz.command;

import java.util.logging.Level;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.strayyakits.kreashenz.Lilypad;
import com.strayyakits.kreashenz.utils.Functions;

public class CmdMain implements CommandExecutor {

	private Lilypad plugin;

	public CmdMain(Lilypad plugin){
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("lilypad")){
			if(s instanceof Player){
				Player p = (Player)s;
				if(args.length == 0){
					sendHelp(p);
				} else {
					if(args[0].equalsIgnoreCase("help")){
						sendHelp(p);
					} else if(args[0].equalsIgnoreCase("leave")){
						if(p.hasPermission("lilypad.leave")){
							new CmdLeave(plugin).execute(s, cmd, args);
						} else Functions.noPerm(p);
					} else if(args[0].equalsIgnoreCase("join")){
						if(p.hasPermission("lilypad.join")){
							new CmdJoin(plugin).execute(s, cmd, args);
						} else Functions.noPerm(p);
					} else if(args[0].equalsIgnoreCase("create")){
						if(p.hasPermission("lilypad.create")){
							new CmdCreate(plugin).execute(s, cmd, args);
						} else Functions.noPerm(p);
					} else if(args[0].equalsIgnoreCase("host")){
						if(p.hasPermission("lilypad.host")){
							new CmdHost(plugin).execute(s, cmd, args);
						} else Functions.noPerm(p);
					} else {
						Functions.tell(p, "§cUnknown sub-command. Please use §f/lp help");
					}
				}
			} else Functions.log(Level.WARNING, "You must be a player to use these commands!");
		}
		return true;
	}

	private void sendHelp(Player p){
		p.sendMessage("§7-§8=§7-§8=§7-§8=§7-§8=§7-§8=§7-§8=§7-§8=§7-§8=§7-§8=§7-§8= §f[§9LilySpleef§f] §8=§7-§8=§7-§8=§7-§8=§7-§8=§7-§8=§7-§8=§7-§8=§7-§8=");
		p.sendMessage("§");
	}

}
