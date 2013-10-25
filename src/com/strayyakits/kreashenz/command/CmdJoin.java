package com.strayyakits.kreashenz.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.strayyakits.kreashenz.Lilypad;
import com.strayyakits.kreashenz.game.Arena;
import com.strayyakits.kreashenz.game.ArenaUtils;
import com.strayyakits.kreashenz.game.Utils;
import com.strayyakits.kreashenz.utils.Functions;

public class CmdJoin extends ICommand {

	protected CmdJoin(Lilypad plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		Player p = (Player)s;

		if(!(p.hasPermission("lilypad.join"))){
			Functions.noPerm(p);
			return;
		}

		if(args.length != 2){
			Functions.tell(p, "§cInvalid arguments! Usage : §f/lp join <arena>");
			return;
		}

		if(Utils.isPlaying(p)){
			Functions.tell(p, "§cYou're already playing. You must §fleave §cto join a new game.");
			return;
		}

		if(!(ArenaUtils.isArena(args[1]))){
			Functions.tell(p, "§cThat arena wasn't found!");
			return;
		}

		Arena arena = ArenaUtils.getArena(args[1]);

		if(!(arena.isRunning())){
			Functions.tell(p, "§cThat arena has already started!");
			return;
		}

		arena.addPlayer(p);
		Functions.tell(p, "§6You successfully joined the §9" + arena.getName() + " §6arena!");
		Functions.broadcast("§6" + p.getName() + " §9joined the LilySpleef game! §7[§6" + arena.getAllPlayers().size() + " §9players§7]");
	}

}