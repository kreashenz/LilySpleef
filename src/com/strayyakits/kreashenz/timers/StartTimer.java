package com.strayyakits.kreashenz.timers;

import com.strayyakits.kreashenz.Lilypad;
import com.strayyakits.kreashenz.game.Arena;

public class StartTimer {
	
	private Arena arena;
	private float time;
	
	private Lilypad plugin;
	
	public StartTimer(Arena arena, float time){
		this.arena = arena;
		this.time = time;
		
		plugin = Lilypad.getInstance();
	}
	
	public void startTimer(){
		
	}

}
