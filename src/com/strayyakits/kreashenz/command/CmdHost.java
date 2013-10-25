package com.strayyakits.kreashenz.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.strayyakits.kreashenz.Lilypad;
import com.strayyakits.kreashenz.game.Arena;
import com.strayyakits.kreashenz.game.ArenaUtils;
import com.strayyakits.kreashenz.game.Game;
import com.strayyakits.kreashenz.utils.Functions;
import com.strayyakits.kreashenz.utils.timers.Timer;

public class CmdHost extends ICommand {

	protected CmdHost(Lilypad plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		Player p = (Player)s;
		if(args.length == 2){
			if(ArenaUtils.isArena(args[1])){
				Arena arena = ArenaUtils.getArena(args[1]);
				Game game = new Game(args[1]);
				if(!game.isRunning() || !game.inCountdown()){
					new Timer(arena, 120*20).startTimer();
				} else Functions.tell(p, "§cThat arena is already running!");
			} else Functions.tell(p, "§cThat arena wasn't found!");
		} else if(args.length == 3){
			if(ArenaUtils.isArena(args[1])){
				Arena arena = ArenaUtils.getArena(args[1]);
				if(!arena.isRunning() || !arena.inCountdown()){
					if(isInt(args[2])){
						new Timer(arena, Integer.parseInt(args[2])).startTimer();
					}
				} else Functions.tell(p, "§cThat arena is already running!");
			} else Functions.tell(p, "§cThat arena wasn't found!");
		} else {
			Functions.tell(p, "§cInvalid arguments! Usage : §f/lp host <arena> [time]");
			Functions.tell(p, "§7Default time is 2 minutes before the game starts.");
		}
	}

	private boolean isInt(String sInt){
		try{
			Integer.parseInt(sInt);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}

}
