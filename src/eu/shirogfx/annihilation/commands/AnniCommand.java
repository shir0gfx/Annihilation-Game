package eu.shirogfx.annihilation.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import eu.shirogfx.annihilation.Main;

public class AnniCommand implements CommandExecutor {
    private Main plugin;

    public AnniCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix").replace("&", "§");
        
        if (args.length == 0) {
            sender.sendMessage(prefix + " §bAnnihilation §cv" + plugin.getDescription().getVersion() + " §7by §bcoasterman10 §7& §bstuntguy3000.");
            sender.sendMessage(prefix + " §7Recoded by: §bshirogfx");
            sender.sendMessage("");
            sender.sendMessage("§8===========[ §bPomoc §8]===========");
            sender.sendMessage(prefix + " §b/anni §7► §3Zobrazi info o pluginu");
            sender.sendMessage(prefix + " §b/anni start §7► §3Startne hru");
            sender.sendMessage(prefix + " §b/stats §7► §3Zobrazi staty");
            sender.sendMessage(prefix + " §b/team §7► §3Zobrazi info o teamoch");
            sender.sendMessage(prefix + " §b/team <team> §7► §3Pripoji vas do teamu");
        }
        
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("start")) {
                if (sender.hasPermission("anni.command.start")) {
                    if (!plugin.startTimer()) {
                        sender.sendMessage(prefix + " §cHra uz startnuta je!");
                    } else {
                        sender.sendMessage(prefix + " §bHra bola odstartovana!");
                    }
                } else sender.sendMessage(prefix + " §cNemas pravo na tento prikaz!");
            }
        }
        return false;
    }
}