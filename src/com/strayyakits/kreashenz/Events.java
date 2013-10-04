package com.strayyakits.kreashenz;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.strayyakits.kreashenz.game.Arena;
import com.strayyakits.kreashenz.game.ArenaUtils;

public class Events implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		for(Arena arena : ArenaUtils.getAllArenas()){
			if(arena.isPlaying(p)){
				if(e.getTo().getBlock().getType().equals(Material.STATIONARY_WATER) || e.getTo().getBlock().getType().equals(Material.WATER)){
					
				}
			}
		}
	}
	
}
