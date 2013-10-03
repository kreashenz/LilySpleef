package com.strayyakits.kreashenz.game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.strayyakits.kreashenz.Lilypad;

public class Arena {

	private Lilypad plugin;

	private File file;
	private FileConfiguration conf;

	private String name;

	private Map map;
	
	private int min = 1;
	
	private List<String> all = new ArrayList<String>();

	public Arena(String name, Map map){
		this.name = name;
		this.map = map;

		plugin = Lilypad.getInstance();

		file = new File(plugin.getDataFolder() + File.separator + "arenas.yml");
		conf = YamlConfiguration.loadConfiguration(file);

	}
	
	public void start(int roundLength){
		if(all.size() > min){
			
		}
	}

	public List<Player> getAllPlayers(){
		List<Player> all = new ArrayList<Player>();
		for(String s : this.all){
			Player p = Bukkit.getPlayerExact(s);
			all.add(p);
		}
		return all;
	}

	public Map getMap(){
		return map;
	}

	public File getFile(){
		return file;
	}

	public FileConfiguration getConfig(){
		return conf;
	}

	public String getName(){
		return name;
	}

}
