package com.strayyakits.kreashenz.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import com.strayyakits.kreashenz.Lilypad;
import com.strayyakits.kreashenz.game.Arena;
import com.strayyakits.kreashenz.game.ArenaUtils;
import com.strayyakits.kreashenz.game.Map;
import com.strayyakits.kreashenz.game.MapUtils;
import com.strayyakits.kreashenz.utils.Functions;
import com.strayyakits.kreashenz.utils.WEUtils;

public class CmdCreate extends ICommand {

	protected CmdCreate(Lilypad plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		Player p = (Player)s;

		if(args.length == 3){
			if(args[1].equalsIgnoreCase("map")){
				if(!MapUtils.isMap(args[2])){
					Selection sel = WEUtils.getWorldEdit().getSelection(p);
					if(sel != null){
						sel = new CuboidSelection(sel.getWorld(), sel.getMaximumPoint(), sel.getMinimumPoint());
						MapUtils.createMap(args[2], sel);
						Functions.tell(p, "§6Successfully created map \"§9" + args[2] + "§6\"!");
					} else Functions.tell(p, "§cPlease choose a selection with WorldEdit.");
				} else Functions.tell(p, "§cThat map name is already in use! Try a different name.");
			} else Functions.tell(p, "§cUnknown sub-command. Use : §f/lp help §cfor help!");
		} else if(args.length == 5){
			if(args[1].equalsIgnoreCase("arena")){
				if(!ArenaUtils.isArena(args[2])){
					if(args[3].equalsIgnoreCase("map")){
						if(MapUtils.isMap(args[4]) && !MapUtils.isMapUsed(args[4])){
							Map map = MapUtils.getMap(args[4]);
							Arena arena = new Arena(args[2], map);
							map.setArena(arena);
							ArenaUtils.addArena(arena);
							ArenaUtils.load();
							Functions.tell(p, "§6Successfully created arena §9" + args[2] + " §6linked with map §9" + map.getName() + "§6!");
						} else Functions.tell(p, "§cThat map is already being used or isn't found!");
					} else Functions.tell(p, "§cInvalid arena creation. Use §f/lp create [arena | map] §cfor help.");
				} else Functions.tell(p, "§cThat arena name is already in use! Try a different name.");
			} else Functions.tell(p, "§cUnknown sub-command. Use : §f/lp help §cfor help!");
		} else {
			if(args[1].equalsIgnoreCase("map")){
				Functions.tell(p, "§cInvalid arguments! Usage : §f/lp create map <name>");
			} else if(args[1].equalsIgnoreCase("arena")){
				Functions.tell(p, "§cInvalid arguments! Usage : §f/lp create arena <name> map <map>");
			} else {
				Functions.tell(p, "§cUnknown sub-command. Use : §f/lp help §cfor help!");
			}
		}

	}

}
