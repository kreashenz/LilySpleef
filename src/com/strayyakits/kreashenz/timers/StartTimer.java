package com.strayyakits.kreashenz.timers;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.strayyakits.kreashenz.Lilypad;
import com.strayyakits.kreashenz.game.Arena;

public class StartTimer {

	private Arena arena;
	private int time;

	private Lilypad plugin;

	public StartTimer(Arena arena, int time){
		this.arena = arena;
		this.time = time;

		plugin = Lilypad.getInstance();
	}

	public void startTimer(){
		new BukkitRunnable(){
			public void run(){
				if(time == 120){
					Bukkit.broadcastMessage("§9New LilySpleef game on map §6" + arena.getMap().getName() + " §92 minutes left to join!");
				} else if(time == 60){
					Bukkit.broadcastMessage("§9New LilySpleef game on map §6" + arena.getMap().getName() + " §91 minute left to join!");
				} else if(time == 30 || time == 20 || time == 10 || (time <= 5 && time > 1)){
					Bukkit.broadcastMessage("§9New LilySpleef game on map §6" + arena.getMap().getName() + " §9" + time +" seconds left to join!");					
				} else if(time == 0){
					Bukkit.broadcastMessage("§9LilySpleef has started!");
					arena.start(time);
					this.cancel();
					return;
				}
				time -= 1;
			}
		}.runTaskTimer(plugin, 20L, 20L);
	}

}
