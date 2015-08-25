package eu.shirogfx.annihilation.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import eu.shirogfx.annihilation.Main;
import eu.shirogfx.annihilation.manager.VotingManager;


public class VoteCommand implements CommandExecutor {
    private final VotingManager manager;

    public VoteCommand(VotingManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	final String prefix = Main.getInstance().getConfig().getString("prefix").replace("&", "�");
        if (!manager.isRunning())
            sender.sendMessage(prefix + " �cHlasovanie uz skoncilo!");
        else if (args.length == 0)
            listMaps(sender);
        else if (!manager.vote(sender, args[0])) {
            sender.sendMessage(prefix + " �cTato mapa neexistuje!");
            listMaps(sender);
        }
        return true;
    }

    private void listMaps(CommandSender sender) {
    	final String prefix = Main.getInstance().getConfig().getString("prefix").replace("&", "�");
        sender.sendMessage(prefix + " �7Mapy:");
        int count = 0;
        for (String map : manager.getMaps().values()) {
            count ++;
            sender.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.AQUA + "[" + count + "] " + ChatColor.GRAY + map);
        }
    }
}
