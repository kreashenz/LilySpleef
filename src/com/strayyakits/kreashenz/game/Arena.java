package com.strayyakits.kreashenz.game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.strayyakits.kreashenz.Functions;
import com.strayyakits.kreashenz.Lilypad;

public class Arena {

	private Lilypad plugin;

	private File file;
	private FileConfiguration conf;

	private String name;

	private Map map;

	private int min = 1;

	private List<Player> allPlayer = new ArrayList<Player>();

	private boolean running = false;

	public Arena(String name, Map map){
		this.name = name;
		this.map = map;

		plugin = Lilypad.getInstance();

		file = new File(plugin.getDataFolder() + File.separator + "arenas.yml");
		conf = YamlConfiguration.loadConfiguration(file);

	}

	public void start(int roundLength){
		if(allPlayer.size() > min){
			running = true;
			spawn(getAllPlayers());
			sendMessage("§6Game has started! §cRUN YOUR ASSES OFF!");
		}
	}

	public void end(){
		if(allPlayer.size() == 1){
			for(Player p : getAllPlayers()){
				Functions.tell(p, "§6You have won the Spleef!");
				p.teleport(Utils.getSaveLoc(p));
				Functions.broadcast("§6" + p.getDisplayName() + " §chas won the Spleef!");
			}
		} else {
			Functions.broadcast("§cNo one won that round!");
		}
	}

	public void addPlayer(Player p){
		if(!(isPlaying(p))){
			allPlayer.add(p);
			Utils.saveLocation(p, p.getLocation());
		} else Functions.tell(p, "§cYou're already playing this arena!");
	}

	public void removePlayer(Player p){
		if(isPlaying(p)){
			allPlayer.remove(p.getName());
			p.teleport(Utils.getSaveLoc(p));
		}
	}

	public List<Player> getAllPlayers(){
		List<Player> all = new ArrayList<Player>();
		for(Player p : allPlayer){
			all.add(p);
		}
		return all;
	}

	public void spawn(List<Player> players){
		for(Player p : players){
			for(Location loc : map.getSpawns()){
				Block b = loc.getWorld().getBlockAt(loc);
				if (b.getType() != null) {
					if (b.getType() == Material.WATER_LILY){
						p.teleport(b.getLocation());
					}
				}
			}
		}
	}

	public void sendMessage(String msg){
		for(Player p : getAllPlayers()){
			Functions.tell(p, msg);
		}
	}

	public Map getMap(){
		return map;
	}

	public File getFile(){
		return file;
	}

	public FileConfiguration getConfig(){
		return conf;
	}

	public String getName(){
		return name;
	}

	public boolean isPlaying(Player p){
		return allPlayer.contains(p);
	}

	public boolean getRunning(){
		return running;
	}

}
