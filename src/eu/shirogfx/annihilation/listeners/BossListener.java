package eu.shirogfx.annihilation.listeners;


import org.bukkit.Bukkit;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;

import eu.shirogfx.annihilation.Main;
import eu.shirogfx.annihilation.Util;
import eu.shirogfx.annihilation.bar.TitleAPI;
import eu.shirogfx.annihilation.chat.ChatUtil;
import eu.shirogfx.annihilation.object.Boss;
import eu.shirogfx.annihilation.object.PlayerMeta;

public class BossListener implements Listener {

    private Main plugin;

    public BossListener(Main instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void onHit(EntityDamageEvent event) {
        if (event.getEntity() instanceof IronGolem) {
            final IronGolem g = (IronGolem) event.getEntity();
            if (g.getCustomName() == null)
                return;

            final Boss b = plugin.getBossManager().bossNames.get(g
                    .getCustomName());
            if (b == null)
                return;

            if (event.getCause() == DamageCause.VOID) {
                event.getEntity().remove();

                Bukkit.getScheduler().runTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        Boss n = plugin.getBossManager().newBoss(b);
                        plugin.getBossManager().spawn(n);
                    }
                });
            }
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof IronGolem) {
            if (!(event.getDamager() instanceof Player))
                event.setCancelled(true);

            final IronGolem g = (IronGolem) event.getEntity();
            if (g.getCustomName() == null)
                return;

            final Boss b = plugin.getBossManager().bossNames.get(g
                    .getCustomName());
            if (b == null)
                return;


            Bukkit.getScheduler().runTask(plugin, new Runnable() {
                @Override
                public void run() {
                    plugin.getBossManager().update(b, g);
                }
            });
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof IronGolem) {
            IronGolem g = (IronGolem) event.getEntity();
            if (g.getCustomName() == null)
                return;

            Boss b = plugin.getBossManager().bossNames.get(g.getCustomName());
            if (b == null)
                return;

            event.getDrops().clear();

            b.spawnLootChest();

            if (g.getKiller() != null) {
                Player killer = g.getKiller();
                ChatUtil.bossDeath(b, killer, PlayerMeta.getMeta(killer)
                        .getTeam());
                respawn(b);
                killer.giveExpLevels(plugin.getConfig().getInt("KillBossXP"));
                Util.spawnFirework(event.getEntity().getLocation(), PlayerMeta.getMeta(killer).getTeam().getColor(PlayerMeta.getMeta(killer).getTeam()), PlayerMeta.getMeta(killer).getTeam().getColor(PlayerMeta.getMeta(killer).getTeam()));
            } else {
                g.teleport(b.getSpawn());
            }
        }
    }

    private void respawn(final Boss b) {
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                Boss n = plugin.getBossManager().newBoss(b);
                ChatUtil.bossRespawn(b);
                String title = plugin.getConfig().getString("SpawnBossTitle").replace("&", "§").replace("%boss%", b.getBossName());
                String sub = plugin.getConfig().getString("SpawnBossSubTitle").replace("&", "§").replace("%boss%", b.getBossName());
                if (Main.getInstance().getConfig().getBoolean("EnableBossTitle") == true) {
                	TitleAPI.AllTitle(title, sub);
                }
                plugin.getBossManager().spawn(n);
            }
        }, 20 * plugin.respawn * 60);
    }
}