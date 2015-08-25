package eu.shirogfx.annihilation.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.shirogfx.annihilation.Main;
import eu.shirogfx.annihilation.object.GameTeam;
import eu.shirogfx.annihilation.object.PlayerMeta;


public class DistanceCommand implements CommandExecutor {
    private Main plugin;

    public DistanceCommand(Main instance) {
        this.plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
            String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            String prefix = plugin.getConfig().getString("prefix").replace("&", "§");

            if (plugin.getPhase() == 0) {
                p.sendMessage(prefix + " §cHra este nebola odstartovana!");
                return false;
            }

            if (PlayerMeta.getMeta(p).getTeam() == GameTeam.NONE) {
                p.sendMessage(prefix + " §cNiesi pripojeny v ziadnom teame!");
                return false;
            }

            p.sendMessage("§8=========[ §bDistance §8]=========");

            for (GameTeam t : GameTeam.values()) {
                if (t != GameTeam.NONE) {
                    showTeam(p, t);
                }
            }

            p.sendMessage("§8==============================");
        } else {
        	String prefix = plugin.getConfig().getString("prefix").replace("&", "§");
            sender.sendMessage(prefix + " §cTento prikaz moze pisat iba hrac!");
        }

        return true;
    }

    private void showTeam(Player p, GameTeam t) {
        try {
            if (t.getNexus() != null && t.getNexus().getHealth() > 0)
                p.sendMessage(t.coloredName() + ChatColor.GRAY + " Nexus Vzdialenost: " + ChatColor.WHITE + ((int) p.getLocation().distance(t.getNexus().getLocation())) + ChatColor.GRAY + " Blokov");
        } catch (IllegalArgumentException ex) {

        }
    }
}
