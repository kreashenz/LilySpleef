package com.strayyakits.kreashenz;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

public class Lilypad extends JavaPlugin {

	private static Lilypad clazz;

	public void onEnable(){
		clazz = this;

		Functions.log(Level.INFO, "LilySpleef enabled v" + getDescription().getVersion());
	}

	public void onDisable(){

	}

	public static Lilypad getInstance(){
		return clazz;
	}

}
