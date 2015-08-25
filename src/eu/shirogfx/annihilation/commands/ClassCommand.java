package eu.shirogfx.annihilation.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.shirogfx.annihilation.Main;
import eu.shirogfx.annihilation.Util;

public class ClassCommand implements CommandExecutor {
    private Main plugin;

    public ClassCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix").replace("&", "§");
        Player p = (Player) sender;
        
        if (args.length == 0) {
        	if (!(sender instanceof Player)) {
        		sender.sendMessage(prefix + " §cTento prikaz moze pisat iba hrac!");
        	} else {
            	String namei = plugin.getConfig().getString("TitleClassMenu").replace("&", "§");
                Util.showClassSelector(p,
                        namei);
        	}
        }
        
        if (args.length == 1) {
        	if (!(sender instanceof Player)) {
        		sender.sendMessage(prefix + " §cTento prikaz moze pisat iba hrac!");
        	} else {
        	sender.sendMessage(prefix + " §cPouzi: §b/class");
            }
        }
        return false;
    }
}
