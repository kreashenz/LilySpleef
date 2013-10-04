package com.strayyakits.kreashenz;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import com.strayyakits.kreashenz.command.CmdMain;

public class Lilypad extends JavaPlugin {

	private static Lilypad clazz;

	public void onEnable(){
		clazz = this;

		Functions.log(Level.INFO, "LilySpleef enabled v" + getDescription().getVersion());

		getServer().getPluginManager().registerEvents(new Events(), this);
		getCommand("lilypad").setExecutor(new CmdMain(this));

		saveResource("config.yml", false);
	}

	public void onDisable(){
		Functions.log(Level.INFO, "LilySpleef disabled v" + getDescription().getVersion());
	}

	public static Lilypad getInstance(){
		return clazz;
	}

}
