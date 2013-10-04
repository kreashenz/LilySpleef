package com.strayyakits.kreashenz.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.strayyakits.kreashenz.Functions;
import com.strayyakits.kreashenz.Lilypad;
import com.strayyakits.kreashenz.game.Arena;
import com.strayyakits.kreashenz.game.ArenaUtils;
import com.strayyakits.kreashenz.game.Utils;

public class CmdJoin extends ICommand {

	protected CmdJoin(Lilypad plugin) {
		super(plugin);
	}

	// Daaaaaaamn, this method looks VERY heavily block-ish..
	
	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		Player p = (Player)s;
		if(p.hasPermission("lilypad.join")){

			if(args.length == 2){

				if(!Utils.isPlaying(p)){

					if(ArenaUtils.isArena(args[1])){

						Arena arena = ArenaUtils.getArena(args[1]);

						if(!arena.getRunning()){

							arena.addPlayer(p);
							Functions.tell(p, "§6You successfully joined the §9" + arena.getName() + " §6arena!");

						} else Functions.tell(p, "§cThat arena has already started!");

					} else Functions.tell(p, "§cThat arena wasn't found!");

				} else Functions.tell(p, "§cYou're already playing. You must §fleave §cto join a new game.");

			} else Functions.tell(p, "§cInvalid arguments! Usage : §f/lp join <arena>");

		} else Functions.noPerm(p);
	}

}