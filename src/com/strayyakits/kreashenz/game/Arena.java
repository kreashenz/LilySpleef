package com.strayyakits.kreashenz.game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.strayyakits.kreashenz.Lilypad;
import com.strayyakits.kreashenz.utils.Functions;

public class Arena {

	private Lilypad plugin;

	private File file;
	private FileConfiguration conf;

	private String name;
	
	private Map map;

	private int min = 1;

	private List<Player> allPlayer = new ArrayList<Player>();

	private boolean running = false;

	public boolean countdown = false;

	public Arena(String name, Map map){
		this.name = name;
		this.map = map;

		plugin = Lilypad.getInstance();

		file = new File(plugin.getDataFolder() + File.separator + "arenas", "arenas.yml");
		conf = YamlConfiguration.loadConfiguration(file);

	}

	public void start(int start){
		if(allPlayer.size() > min){
			countdown = false;
			running = true;
			spawn(getAllPlayers());
			sendMessage("§6Game has started! §cRUN YOUR ASSES OFF!");
			for(Player p : getAllPlayers()){
				p.getInventory().clear();
				p.getInventory().setArmorContents(null);
				for(PotionEffect pot : p.getActivePotionEffects())p.removePotionEffect(pot.getType());
			}
		}
	}

	public void end(){
		if(running == true){
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
	}

	public void addPlayer(Player p){
		if(!(isPlaying(p))){
			allPlayer.add(p);
		} else Functions.tell(p, "§cYou're already playing this arena!");
	}

	public void removePlayer(Player p){
		if(isPlaying(p)){
			allPlayer.remove(p.getName());
			p.teleport(Utils.getSaveLoc(p));
			Utils.delLocation(p);
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
		List<Location> spawns = map.getSpawns();
		List<Player> ps = players;

		Location spawn = spawns.get((int)(Math.random() * spawns.size()));
		Player p = ps.get((int)(Math.random() * ps.size()));

		p.teleport(spawn);
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

	public boolean isRunning(){
		return running;
	}

	public boolean inCountdown(){
		return countdown;
	}

}
