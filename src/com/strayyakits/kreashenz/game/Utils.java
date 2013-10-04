package com.strayyakits.kreashenz.game;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Utils {

	private static HashMap<Player, Location> savedLoc = new HashMap<Player, Location>();

	public static void saveLocation(Player p, Location loc){
		savedLoc.put(p, p.getLocation());
	}

	public static void delLocation(Player p){
		savedLoc.remove(p);
	}

	public static Location getSaveLoc(Player p){
		int x = (int)savedLoc.get(p.getName()).getX();
		int y = (int)savedLoc.get(p.getName()).getY();
		int z = (int)savedLoc.get(p.getName()).getZ();
		World w = savedLoc.get(p.getName()).getWorld();
		return new Location(w, x, y, z);
	}

	public static boolean isPlaying(Player p){
		for(Arena arena : ArenaUtils.getAllArenas()){
			if(arena.getAllPlayers().contains(p)){
				return true;
			}
		}
		return false;
	}
}
