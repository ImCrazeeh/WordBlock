package me.ImCrazeeh.wb;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	/*
	 * Plugin made by ImCrazeeh, please do not use this code and claim it as your own!
	*/
	
    Permission playerPermission = new Permission("wb.reload");
    Permission playerPermission1 = new Permission("wb.see");
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("wordblock")) {
			if (args.length == 0) {
				if (sender.hasPermission("wb.see")) {
				sender.sendMessage("§8[§4*§8] §6WordBlock Commands §8[§4*§8]");
				sender.sendMessage(" §8[§7*§8] §d/wordblock reload §8- §7§oUpdate your server to be using your new config.");
				}
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("reload")) {
					if (sender.hasPermission("wb.reload")) {
						sender.sendMessage("§4WordBlock v" + this.getDescription().getVersion() + " §chas been reloaded.");
						reloadConfig();
					}
				}
				else {
					if (sender.hasPermission("wb.see")) {
						sender.sendMessage("§7Unknown args.");
					}
				}
			}
		}
		return true;
	}
	

	@EventHandler
	public void blockWords(AsyncPlayerChatEvent e) {
			for (String blocked : getConfig().getStringList("blocked-words")) {
			if (e.getMessage().contains(blocked)) {
				Player p = e.getPlayer();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("blocked-msg")));
				e.setCancelled(true);
			}
		}
	}

}
