package com.strayyakits.kreashenz.game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sk89q.worldedit.bukkit.selections.Selection;
import com.strayyakits.kreashenz.Lilypad;

public class MapUtils {

	private static File file;

	private static Lilypad plugin;

	private static List<Map> all = new ArrayList<Map>();

	static {
		plugin = Lilypad.getInstance();
		file = new File(plugin.getDataFolder() + File.separator + "maps");
	}

	private MapUtils(){
		throw new UnsupportedOperationException("You cannot create an instance of this class!");
	}

	public static void load(){
		all.clear();

		for(File mf : file.listFiles()){
			if(!(mf.getName().startsWith("."))){
				String name = mf.getName().replace(".yml", "");
				Map map = new Map(name);

				all.add(map);
			}
		}

	}

	public static void createMap(String name, Selection sec){
		if(!(isMap(name))){
			Map map = new Map(name);
			map.setPoint1(sec.getMinimumPoint());
			map.setPoint2(sec.getMaximumPoint());

			map.save();
		}
	}

	public static void deleteMap(String name){
		if(!(isMap(name))){
			Map map = new Map(name);
			map.getFile().delete();
		}
	}

	public static File getMapsFolder(){
		return file;
	}

	public static boolean isMap(String name){
		for(Map map : all){
			if(map.getName().equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}

	public static Map getMap(String name){
		Map map = null;
		if(isMap(name)){
			for(Map m : all){
				if(m.getName().equalsIgnoreCase(name)){
					map = m;
				}
			}
		}
		return map;
	}

	public static boolean isMapUsed(String name){
		boolean used = false;

		for(Arena arena : ArenaUtils.getAllArenas()){
			if(arena.getMap().getName().equalsIgnoreCase(name)){
				used = true;
			}
		}

		return used;
	}

}
