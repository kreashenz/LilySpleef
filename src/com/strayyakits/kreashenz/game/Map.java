package com.strayyakits.kreashenz.game;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.strayyakits.kreashenz.Lilypad;

public class Map {

	private Lilypad plugin;

	private String name;

	private File file;
	private FileConfiguration conf;

	private Location loc1;
	private Location loc2;
	private Location spawn;

	private Arena arena;

	public Map(String name){
		this.name = name;

		plugin = Lilypad.getInstance();

		file = new File(plugin.getDataFolder() + File.separator + name + ".yml");
		if(file.exists()){
			conf = YamlConfiguration.loadConfiguration(file);
		} else {
			try {
				file.createNewFile();
				conf = YamlConfiguration.loadConfiguration(file);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}

	}

	public void setArena(Arena arena){
		this.arena = arena;
	}

	public void setSpawn(Location loc){
		this.spawn = loc;

		conf.set("spawn.x", loc.getBlockX());
		conf.set("spawn.y", loc.getBlockX());
		conf.set("spawn.z", loc.getBlockX());
		conf.set("spawn.world", loc.getWorld().getName());
		save();
	}

	public void setPoint1(Location loc){
		this.loc1 = loc;

		conf.set("location1.x", loc.getBlockX());
		conf.set("location1.y", loc.getBlockX());
		conf.set("location1.z", loc.getBlockX());
		conf.set("location1.world", loc.getWorld().getName());
		save();
	}

	public void setPoint2(Location loc){
		this.loc2 = loc;

		conf.set("location2.x", loc.getBlockX());
		conf.set("location2.y", loc.getBlockX());
		conf.set("location2.z", loc.getBlockX());
		conf.set("location2.world", loc.getWorld().getName());
		save();
	}

	public Location getSpawn(){
		return spawn;
	}

	public Location getPoint1(){
		return loc1;
	}

	public Location getPoint2(){
		return loc2;
	}

	public String getName(){
		return name;
	}

	public Arena getArena(){
		return arena;
	}

	public File getFile(){
		return file;
	}

	public FileConfiguration getConfig(){
		return conf;
	}

	private void save(){
		try {
			conf.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
