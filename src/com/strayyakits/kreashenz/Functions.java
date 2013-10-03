package com.strayyakits.kreashenz;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Functions {

	public static void tell(Player p, String msg){
		p.sendMessage("§f[§9LilySpleef§f] §6" + msg);
	}

	public static void log(Level level, String log){
		Bukkit.getLogger().log(level, log);
	}

}
