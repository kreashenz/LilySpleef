package com.strayyakits.kreashenz.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.strayyakits.kreashenz.Functions;
import com.strayyakits.kreashenz.Lilypad;
import com.strayyakits.kreashenz.game.ArenaUtils;
import com.strayyakits.kreashenz.game.Utils;

public class CmdLeave extends ICommand {

	protected CmdLeave(Lilypad plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		Player p = (Player)s;
		if(p.hasPermission("lilypad.leave")){
			if(Utils.isPlaying(p)){
				ArenaUtils.getArena(p).removePlayer(p);
				Functions.tell(p, "§6You have successfully left the game.");
			} else Functions.tell(p, "§cYou're not playing a game yet.");
		} else Functions.noPerm(p);
	}

}