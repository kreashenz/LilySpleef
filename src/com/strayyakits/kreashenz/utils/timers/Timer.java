package com.strayyakits.kreashenz.utils.timers;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.strayyakits.kreashenz.Lilypad;
import com.strayyakits.kreashenz.game.Game;
import com.strayyakits.kreashenz.game.GameState;

public class Timer {

	private Game game;
	private int startTime;
	private int endTime = 2400*20;

	private Lilypad plugin;

	public Timer(Game game, int startTime){
		this.game = game;
		this.startTime = startTime * 20;

		plugin = Lilypad.getInstance();
	}

	public void startTimer(){
		game.setState(GameState.COUNTDOWN);
		new BukkitRunnable(){
			public void run(){
				if(startTime == 120){
					Bukkit.broadcastMessage("§9New LilySpleef game on arena §6" + game.getName() + " §92 minutes left to join!");
				} else if(startTime == 60){
					Bukkit.broadcastMessage("§9New LilySpleef game on arena §6" + game.getName() + " §91 minute left to join!");
				} else if(startTime == 30 || startTime == 20 || startTime == 10 || (startTime <= 5 && startTime > 1)){
					Bukkit.broadcastMessage("§9New LilySpleef game on arena §6" + game.getName() + " §9" + startTime +" seconds left to join!");					
				} else if(startTime == 0){
					Bukkit.broadcastMessage("§9LilySpleef has started!");
					game.start(startTime);
					game.setState(GameState.RUNNING);
					this.cancel();
					return;
				}
				startTime -= 1;
			}
		}.runTaskTimer(plugin, 20L, 20L);
	}

	public void endTimer(){
		game.setState(GameState.RUNNING);
		new BukkitRunnable(){
			public void run(){
				if(endTime == 120){
					Bukkit.broadcastMessage("§9LilySpleef ending in §62 minutes§9!");
				} else if(endTime == 60){
					Bukkit.broadcastMessage("§9LilySpleef ending in §61 minute§9!");
				} else if(endTime == 30 || endTime == 20 || endTime == 10 || (endTime <= 5 && endTime > 1)){
					Bukkit.broadcastMessage("§9LilySpleef ending in §6" + endTime + " seconds§9!");					
				} else if(endTime == 0){
					Bukkit.broadcastMessage("§9LilySpleef has ended!");
					game.end();
					game.setState(GameState.STOPPED);
					this.cancel();
					return;
				}
				endTime -= 1;
			}
		}.runTaskTimer(plugin, 20L, 20L);
	}

}
