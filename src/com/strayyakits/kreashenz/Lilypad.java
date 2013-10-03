package com.strayyakits.kreashenz;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

public class Lilypad extends JavaPlugin {

	public void onEnable(){
		Functions.log(Level.INFO, "LilySpleef enabled v" + getDescription().getVersion());
	}

	public void onDisable(){
		
	}

}
