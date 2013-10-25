package com.strayyakits.kreashenz.command;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.strayyakits.kreashenz.Lilypad;
import com.strayyakits.kreashenz.game.Map;
import com.strayyakits.kreashenz.game.MapUtils;

public class CmdMaps extends ICommand {

	protected CmdMaps(Lilypad plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		Player p = (Player)s;

		if(args.length == 2){
			if(args[1].equalsIgnoreCase("arenas")){

			} else if(args[1].equalsIgnoreCase("maps")) {
				StringBuilder aMaps = new StringBuilder();
				StringBuilder uMaps = new StringBuilder();
				StringBuilder allMaps = new StringBuilder();
				for(Map map : getAllMaps()){
					if(allMaps.length() > 0){
						allMaps.append(", ");
					}
					if(aMaps.length() > 0){
						aMaps.append(", ");
					}
					if(uMaps.length() > 0){
						uMaps.append(", ");
					}
					allMaps.append("§6" + map.getName() + "§7");
					if(!MapUtils.isMapUsed(map.getName())){
						aMaps.append("§2" + map.getName() + "§7");
					} else {
						uMaps.append("§c" + map.getName() + "§7");
					}
				}
				p.sendMessage("§6All maps: " + allMaps.toString());
				p.sendMessage("§2Unused maps: " + aMaps.toString());
				p.sendMessage("§cUsed maps: " + uMaps.toString());
			} else {

			}
		} else {

		}
	}

	private List<Map> getAllMaps(){
		List<Map> all = new ArrayList<Map>();

		for(File mf : MapUtils.getMapsFolder().listFiles()){
			if(!(mf.getName().startsWith("."))){
				String name = mf.getName().replace(".yml", "");
				Map map = new Map(name);
				all.add(map);
			}
		}

		return all;
	}

}
