package com.strayyakits.kreashenz.game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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

	private Arena arena;

	public Map(String name, Lilypad instance){
		this.name = name;

		plugin = instance;

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

	public List<Block> getBlocks(){
		List<Block> blocks = new ArrayList<Block>();

		Location loc1 = getPoint1();
		Location loc2 = getPoint2();
		int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
		int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
		int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
		int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
		int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
		int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());

		for(int x = minX; x <= maxX; x++){
			for(int y = minY; y <= maxY; y++){
				for(int z = minZ; z <= maxZ; z++){
					Block b = loc1.getWorld().getBlockAt(x, y, z);
					blocks.add(b);
				}
			}
		}

		return blocks;
	}

	public List<Location> getSpawns(){
		List<Location> spawns = new ArrayList<Location>();

		for(Block b : getBlocks()){
			if(b.getType().equals(Material.WATER_LILY))
				spawns.add(new Location(b.getWorld(), b.getLocation().getBlockX(), b.getLocation().getY() + 1, b.getLocation().getBlockZ()));
		}

		return spawns;
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

	protected void save(){
		try {
			conf.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
