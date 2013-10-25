package com.strayyakits.kreashenz.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import com.strayyakits.kreashenz.Lilypad;
import com.strayyakits.kreashenz.game.Map;

public class WEUtils {

	private static WorldEditPlugin we;

	static {
		Plugin pl = Lilypad.getInstance().getServer().getPluginManager().getPlugin("WorldEdit");
		if(pl instanceof WorldEditPlugin){
			we = (WorldEditPlugin)pl;
		}
	}

	public static boolean isInMap(Player player, Map map){
		return isInMap(player.getLocation(), map);
	}

	public static boolean isInMap(Location loc, Map map){
		Selection selection = new CuboidSelection(map.getPoint1().getWorld(), map.getPoint1(), map.getPoint2());
		return selection.contains(loc);
	}

	public static WorldEditPlugin getWorldEdit(){
		return we;
	}

}