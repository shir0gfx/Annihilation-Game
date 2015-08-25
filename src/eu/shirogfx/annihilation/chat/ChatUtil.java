package eu.shirogfx.annihilation.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import eu.shirogfx.annihilation.Main;
import eu.shirogfx.annihilation.Util;
import eu.shirogfx.annihilation.VaultHooks;
import eu.shirogfx.annihilation.bar.TitleAPI;
import eu.shirogfx.annihilation.object.Boss;
import eu.shirogfx.annihilation.object.GameTeam;
import eu.shirogfx.annihilation.object.PlayerMeta;

public class ChatUtil {

    private static final String RESET = ChatColor.RESET.toString();


    private static boolean roman = false;

    public static void setRoman(boolean b) {
        roman = b;
    }

    public static void allMessage(GameTeam team, Player sender, String message,
            boolean dead) {
        String group;
        String username;
        if (team == GameTeam.NONE) {
            group = Main.getInstance().getConfig().getString("LobbyChat").replace("&", "§");
            username = RESET + sender.getName();
        } else {
            group = Main.getInstance().getConfig().getString("AllChat").replace("&", "§").replace("%color%", String.valueOf(team.color()));
            username = team.color() + sender.getName();
            if (dead) {
                group = Main.getInstance().getConfig().getString("DeadChat").replace("&", "§") + " "
                        + group;
            }
        }
        String msg = message;
        String permGroup = VaultHooks.getGroup(sender.getName());
        if (!permGroup.equals(""))
            group += " " + permGroup + RESET;
        String toSend = group + " " + username + RESET + " " + Main.getInstance().getConfig().getString("ChatEnd").replace("&", "§") + "§r " + msg;
        for (Player player : Bukkit.getOnlinePlayers())
            player.sendMessage(toSend);
    }

    public static void teamMessage(GameTeam team, Player sender,
            String message, boolean dead) {
        String group;
        if (team == GameTeam.NONE) {
            allMessage(team, sender, message, false);
            return;
        } else {
            group = Main.getInstance().getConfig().getString("TeamChat").replace("&", "§").replace("%color%", String.valueOf(team.color()));
            if (dead) {
                group = Main.getInstance().getConfig().getString("DeadChat").replace("&", "§") + " " + group;
            }
        }
        String permGroup = VaultHooks.getGroup(sender.getName());
        if (!permGroup.equals(""))
            group += " " + permGroup + RESET;
        String toSend = group + " " + team.color() + sender.getName() + RESET + " " + Main.getInstance().getConfig().getString("ChatEnd").replace("&", "§") + "§r " + message;
        for (Player player : team.getPlayers())
            player.sendMessage(toSend);
    }

    public static void broadcast(String message) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                message));
    }

    public static void nexusDestroyed(GameTeam attacker, GameTeam victim, Player p) {
    	String title = Main.getInstance().getConfig().getString("NexusZnicenTitle").replace("&", "§").replace("%ZNICEN%", victim.coloredName()).replace("%KILLER%", attacker.color().toString() + p.getName()).replace("%TEAM%", attacker.coloredName());
    	String sub = Main.getInstance().getConfig().getString("NexusZnicenSubTitle").replace("&", "§").replace("%ZNICEN%", victim.coloredName()).replace("%KILLER%", attacker.color().toString() + p.getName()).replace("%TEAM%", attacker.coloredName());
        broadcast("§8===============[ §bNexus Zniceny §8]===============");
        broadcast(attacker.color().toString() + p.getName() + " §7z teamu §r" + attacker.coloredName() + " §7znicil " + victim.coloredName() + " §7Nexus!");
        broadcast("§8==============================================");
        if (Main.getInstance().getConfig().getBoolean("EnableNexusTitle") == true) {
        	TitleAPI.AllTitle(title, sub);
        }
    }

    public static String nexusBreakMessage(Player breaker, GameTeam attacker,
            GameTeam victim) {
        return colorizeName(breaker, attacker) + " §7teamu "
                + victim.coloredName() + " §7nici Nexus!";
    }

    private static String colorizeName(Player player, GameTeam team) {
        return team.color() + player.getName();
    }

    public static void phaseMessage(int phase) {
    	String title = Main.getInstance().getConfig().getString("PhaseTitle").replace("&", "§").replace("%PHASE%", translateRoman(phase));
    	String sub = Main.getInstance().getConfig().getString("PhaseSubTitle").replace("&", "§").replace("%PHASE%", translateRoman(phase));
        broadcast("§8===========[ §bPostup §8]===========");
        broadcast(Util.getPhaseColor(phase) + "Faza " + translateRoman(phase) + " §7zacala!");
        switch (phase) {
        case 1:
            broadcast("§7Nexusi sa budu dat nicit vo fazi " + translateRoman(2));
            if (Main.getInstance().getConfig().getBoolean("EnablePhaseTitle") == true) {
            	TitleAPI.AllTitle(title, sub);
            }
            break;
        case 2:
            broadcast("§7Nexusi sa daju nicit!");
            broadcast("§7Bossovia sa spawnuli!");
            if (Main.getInstance().getConfig().getBoolean("EnablePhaseTitle") == true) {
            	TitleAPI.AllTitle(title, sub);
            }
            break;
        case 3:
            broadcast("§bDiamanty §7sa spawnuli uprostred mapy!");
            if (Main.getInstance().getConfig().getBoolean("EnablePhaseTitle") == true) {
            	TitleAPI.AllTitle(title, sub);
            }
            break;
        case 4:
            if (Main.getInstance().getConfig().getBoolean("EnablePhaseTitle") == true) {
            	TitleAPI.AllTitle(title, sub);
            }
            break;
        case 5:
            broadcast("§cDvojity nexus damage!");
            if (Main.getInstance().getConfig().getBoolean("EnablePhaseTitle") == true) {
            	TitleAPI.AllTitle(title, sub);
            }
        }
        broadcast("§8================================");
    }

    public static void winMessage(GameTeam winner) {
    	String title = Main.getInstance().getConfig().getString("EndGameTitle").replace("&", "§").replace("%TEAM%", winner.coloredName());
    	String sub = Main.getInstance().getConfig().getString("EndGameSubTitle").replace("&", "§").replace("%TEAM%", winner.coloredName());
        broadcast("§8================[ §bKonec Hry §8]================");
        broadcast("§7Team §r" + winner.coloredName() + " §7Vyhral Annihilation!");
        broadcast("");
        broadcast("§7Vela stastia aj nabude vam praje http://Shotwar.eu ! §c§lRestartujem...");
        broadcast("§8=============================================");
        if (Main.getInstance().getConfig().getBoolean("EnableEndTitle") == true) {
        	TitleAPI.AllTitle(title, sub);
        }
    }

    public static void bossDeath(Boss b, Player killer, GameTeam team) {
        broadcast("§8==========[ §bBoss Zabity §8]==========");
        broadcast("§7Boss §r" + b.getBossName() + " §7bol zabity hracom " + colorizeName(killer, team));
        broadcast("§8===================================");
    }

    public static void bossRespawn(Boss b) {
        broadcast("§8================[ §bBoss §8]================");
        broadcast("§7Boss §r" + b.getBossName() + " §7bol spawnuty! Bez ho zabit!!");
        broadcast("§8========================================");
    }

    public static String formatDeathMessage(Player victim, Player killer,
            String original) {
        GameTeam killerTeam = PlayerMeta.getMeta(killer).getTeam();
        String killerColor = killerTeam != null ? killerTeam.color().toString()
                : ChatColor.DARK_PURPLE.toString();
        String killerName = killerColor + killer.getName() + ChatColor.GRAY;

        String message = ChatColor.GRAY + formatDeathMessage(victim, original);
        message = message.replace(killer.getName(), killerName);

        return message;
    }

    public static String formatDeathMessage(Player victim, String original) {
        GameTeam victimTeam = PlayerMeta.getMeta(victim).getTeam();
        String victimColor = victimTeam != null ? victimTeam.color().toString()
                : ChatColor.DARK_PURPLE.toString();
        String victimName = victimColor + victim.getName() + ChatColor.GRAY;

        String message = ChatColor.GRAY + original;
        message = message.replace(victim.getName(), victimName);

        if (message.contains("")) {
            String[] arr = message.split("");
            message = arr[0];
        }

        return message.replace("was slain by", "bol zabity hracom");
    }

    public static String translateRoman(int number) {
        if (!roman)
            return String.valueOf(number);

        switch (number) {
        case 0:
            return "0";
        case 1:
            return "I";
        case 2:
            return "II";
        case 3:
            return "III";
        case 4:
            return "IV";
        case 5:
            return "V";
        case 6:
            return "VI";
        case 7:
            return "VII";
        case 8:
            return "VIII";
        case 9:
            return "IX";
        case 10:
            return "X";
        default:
            return String.valueOf(number);
        }
    }

    private ChatUtil() {
    }
}
