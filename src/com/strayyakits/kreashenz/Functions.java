package com.strayyakits.kreashenz;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Functions {

	public static void tell(Player p, String msg){
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "§f[§9LilySpleef§f] §6" + msg));
	}

	public static void log(Level level, String log){
		Bukkit.getLogger().log(level, "[LilySpleef] " + log);
	}

	public static void noPerm(Player p){
		tell(p, Lilypad.getInstance().getConfig().getString("no-permission"));
	}
	
	public static void broadcast(String msg){
		for(Player p : Bukkit.getOnlinePlayers()){
			tell(p, msg);
		}
	}
}
