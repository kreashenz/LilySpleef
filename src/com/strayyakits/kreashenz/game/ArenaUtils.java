package com.strayyakits.kreashenz.game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ArenaUtils {

	private static List<Arena> all = new ArrayList<Arena>();

	public static void load(){
		all.clear();

		for(File maps : MapUtils.getMapsFolder().listFiles()){
			String name = maps.getName().replace(".yml", "");
			FileConfiguration conf = YamlConfiguration.loadConfiguration(maps);

			if(conf.isString("arena")){
				String aName = conf.getString("arena");
				Map map = MapUtils.getMap(name);
				Arena arena = new Arena(aName, map);

				all.add(arena);
			}
		}

	}

	public static void addArena(Arena arena){
		if(!(all.contains(arena))){
			all.add(arena);
		}
	}

	public static void removeArena(Arena arena){
		if(all.contains(arena)){
			all.remove(arena);
		}
	}

	public static void deleteArena(Arena arena){
		removeArena(arena);
		Map map = arena.getMap();
		FileConfiguration mapConf = map.getConfig();
		if(mapConf != null){
			if(mapConf.isSet("arena"))
				mapConf.set("arena", null);
		}
	}

	public static List<Arena> getAllArenas(){
		return all;
	}

	public static Arena getArena(String name){
		Arena arena = null;
		if(isArena(name)){
			for(Arena a : all){
				if(a.getName().equalsIgnoreCase(name)){
					arena = a;
				}
			}
		}
		return arena;
	}

	public static Arena getArena(Player p){
		Arena arena = null;
		for(Arena arenas : getAllArenas()){
			if(arenas.getAllPlayers().contains(p)){
				arena = arenas;
			}
		}
		return arena;
	}

	public static boolean isArena(String name){
		boolean exists = false;
		for(Arena arena : getAllArenas()){
			if(arena != null && arena.getName() != null){
				if(arena.getName().equalsIgnoreCase(name)){
					exists = true;
				}
			}
		}
		return exists;
	}

	public static boolean isRunning(Map map){
		for(Arena arena : getAllArenas()){
			if(arena.getMap() == map){
				return true;
			}
		}
		return false;
	}

}
