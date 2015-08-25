package eu.shirogfx.annihilation.object;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import eu.shirogfx.annihilation.Main;
import eu.shirogfx.annihilation.listeners.SoulboundListener;
import static org.bukkit.potion.PotionType.INVISIBILITY;

@SuppressWarnings("unused")
public enum Kit {
    CIVILIAN(Material.WORKBENCH) {
        {
            spawnItems.add(new ItemStack(Material.WOOD_SWORD));
            spawnItems.add(new ItemStack(Material.WOOD_PICKAXE));
            spawnItems.add(new ItemStack(Material.WOOD_AXE));
            spawnItems.add(new ItemStack(Material.WORKBENCH));
            lore.add("Si kostrc celeho teamu!");
            lore.add("");
            lore.add("Si posila, palivo");
            lore.add("rambo s tvojim");
            lore.add("drevenym mecom!");
            lore.add("Priprav sa na bitku!");
        }
    },
    WARRIOR(Material.STONE_SWORD) {
        {
            spawnItems.add(new ItemStack(Material.STONE_SWORD));
            spawnItems.add(new ItemStack(Material.WOOD_PICKAXE));
            spawnItems.add(new ItemStack(Material.WOOD_AXE));
            spawnItems.add(new Potion(PotionType.INSTANT_HEAL, 1)
                    .toItemStack(1));
            spawnItems.get(0).addEnchantment(Enchantment.KNOCKBACK, 1);
            lore.add("Ty si mec celeho teamu!");
            lore.add("");
            lore.add("Pri bitke s niekym davas");
            lore.add("+1 uder silnejsi.");
            lore.add("");
            lore.add("Dostanes enchant knockback,");
            lore.add("mec a heal potion.");
            lore.add("Vela stastia pri utoci!");
        }
    },
    ARCHER(Material.BOW) {
        {
            spawnItems.add(new ItemStack(Material.WOOD_SWORD));
            spawnItems.add(new ItemStack(Material.BOW));
            spawnItems.add(new ItemStack(Material.WOOD_PICKAXE));
            spawnItems.add(new ItemStack(Material.WOOD_AXE));
            spawnItems.add(new ItemStack(Material.WOOD_SPADE));
            spawnItems.add(new Potion(PotionType.INSTANT_HEAL, 1)
                    .toItemStack(1));
            spawnItems.add(new ItemStack(Material.ARROW, 16));
            spawnItems.get(1).addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
            lore.add("Si sniper z celeho teamu!");
            lore.add("");
            lore.add("Davas o +1 silnejsi uder.");
            lore.add("Mozes craftit sipy!");
        }
    },

    MINER(Material.STONE_PICKAXE) {
        {
            spawnItems.add(new ItemStack(Material.WOOD_SWORD));
            spawnItems.add(new ItemStack(Material.STONE_PICKAXE));
            spawnItems.add(new ItemStack(Material.WOOD_AXE));
            spawnItems.add(new ItemStack(Material.FURNACE));
            spawnItems.add(new ItemStack(Material.COAL, 4));
            spawnItems.get(1).addEnchantment(Enchantment.DIG_SPEED, 1);
            lore.add("Si otrok celeho teamu!");
            lore.add("");
            lore.add("Dostanes enchant efficiency,");
            lore.add("kamenny krompac. Pri vykopani");
            lore.add("dostanes dvojnasobok rud.");
        }
    },
    LUMBERJACK(Material.STONE_AXE) {
        {
            spawnItems.add(new ItemStack(Material.WOOD_SWORD));
            spawnItems.add(new ItemStack(Material.WOOD_PICKAXE));
            spawnItems.add(new ItemStack(Material.STONE_AXE));
            spawnItems.get(2).addEnchantment(Enchantment.DIG_SPEED, 1);
            lore.add("Si silak!");
            lore.add("");
            lore.add("Dostanes enchant efficiency,");
            lore.add("kamennu sekerku a mozes tazit drevo!");
            lore.add("Za vytazenie dreva dostanes dvojnasobok.");
            lore.add("Potom to drevo mozes vyuzit ako aj");
            lore.add("Material na hradby!");
        }
    },
    SCOUT(Material.FISHING_ROD) {
        {
            spawnItems.add(new ItemStack(Material.GOLD_SWORD));
            spawnItems.add(new ItemStack(Material.FISHING_ROD));
            spawnItems.add(new ItemStack(Material.WOOD_PICKAXE));
            spawnItems.add(new ItemStack(Material.WOOD_AXE));
            ItemMeta meta = spawnItems.get(1).getItemMeta();
            meta.setDisplayName("Grapple");
            spawnItems.get(1).setItemMeta(meta);
            lore.add("Si skaut z celeho teamu!");
            lore.add("");
            lore.add("Vyuzi tvoju rychlost,");
            lore.add("na utocenie, zdrhanie cez hory,");
            lore.add("luky a rieky.");
            lore.add("");
            lore.add("ALE POZOR!");
            lore.add("Ty mozes si obliect iba lahky armor!");
            lore.add("Ziadny chainmail armor.. ten ta spomaluje!");
        }
    },
    BERSERKER(Material.CHAINMAIL_CHESTPLATE) {
        {
            spawnItems.add(new ItemStack(Material.STONE_SWORD));
            spawnItems.add(new ItemStack(Material.WOOD_PICKAXE));
            spawnItems.add(new ItemStack(Material.WOOD_AXE));
            spawnItems.add(new Potion(PotionType.INSTANT_HEAL, 1)
                    .toItemStack(1));
            lore.add("Si tank.");
            lore.add("");
            lore.add("Zacnes hrat so 7 srdieckami.");
            lore.add("Za kazdy kill dostanes extra srdiecko.");
            lore.add("A takto si mozes dobijat srdiecka");
            lore.add("az dokial nebudes mat ich spolu 15!");
        }
    },
    SPY(Material.POTION) {
        {
            spawnItems.add(new ItemStack(Material.STONE_SWORD));
            spawnItems.add(new ItemStack(Material.STONE_PICKAXE));
            spawnItems.add(new ItemStack(Material.STONE_AXE));
            lore.add("Si spion.");
            lore.add("");
            lore.add("Pokial kliknes lavym,");
            lore.add("dostanes potion neviditelnosti na 10 sekund!");
        }
    },
    DEFENDER(Material.IRON_CHESTPLATE) {
        {
            spawnItems.add(new ItemStack(Material.STONE_SWORD));
            spawnItems.add(new ItemStack(Material.STONE_PICKAXE));
            spawnItems.add(new ItemStack(Material.STONE_AXE));
            lore.add("Si obrana.");
            lore.add("");
            lore.add("V teame si dost dolezity,");
            lore.add("musis sa prevazne zdrziavat");
            lore.add("niekde blizko tvojho nexusu,");
            lore.add("aby si ho ochranil!");
        }
    };

    static {
        for (Kit kit : values())
            kit.init();
    }

    private ItemStack icon;
    List<String> lore = new ArrayList<String>();
    List<ItemStack> spawnItems = new ArrayList<ItemStack>();
    ItemStack[] spawnArmor = new ItemStack[] {
            new ItemStack(Material.LEATHER_BOOTS),
            new ItemStack(Material.LEATHER_LEGGINGS),
            new ItemStack(Material.LEATHER_CHESTPLATE),
            new ItemStack(Material.LEATHER_HELMET) };

    Kit(Material m) {
        icon = new ItemStack(m);
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(getName());
        icon.setItemMeta(meta);
    }

    private void init() {
        for (int i = 0; i < lore.size(); i++) {
            String s = lore.get(i);
            s = ChatColor.AQUA + s;
            lore.set(i, s);
        }
        ItemMeta meta = icon.getItemMeta();
        meta.setLore(lore);
        icon.setItemMeta(meta);
    }

    public static Kit getKit(String name) {
        for (Kit type : values()) {
            if (type.name().equalsIgnoreCase(name))
                return type;
        }
        return null;
    }

    public void give(Player recipient, GameTeam team) {
        PlayerInventory inv = recipient.getInventory();
        inv.clear();

        for (ItemStack item : spawnItems) {
            ItemStack toGive = item.clone();
            SoulboundListener.soulbind(toGive);
            inv.addItem(toGive);
        }

        recipient.removePotionEffect(PotionEffectType.SPEED);

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassMeta = compass.getItemMeta();
        Main plugin = Main.getInstance();
        String kompas = plugin.getConfig().getString("CompassItem").replace("&", "§").replace("%TEAM%", String.valueOf(team.toString())).replaceAll("%COLOR%", String.valueOf(team.color()));
        compassMeta.setDisplayName(kompas);
        compass.setItemMeta(compassMeta);
        SoulboundListener.soulbind(compass);

        inv.addItem(compass);
        recipient.setCompassTarget(team.getNexus().getLocation());

        inv.setArmorContents(spawnArmor);
        colorizeArmor(inv, getTeamColor(team));

        for (ItemStack armor : inv.getArmorContents())
            SoulboundListener.soulbind(armor);

        if (this == SCOUT)
            addScoutParticles(recipient);

        if (this == BERSERKER)
            recipient.setMaxHealth(14.0);
        else
            recipient.setMaxHealth(20.0);
    }

    private Color getTeamColor(GameTeam team) {
        switch (team) {
        case RED:
            return Color.RED;
        case YELLOW:
            return Color.YELLOW;
        case GREEN:
            return Color.GREEN;
        case BLUE:
            return Color.BLUE;
        default:
            return Color.WHITE;
        }
    }

    private void colorizeArmor(PlayerInventory inv, Color color) {
        for (ItemStack item : inv.getArmorContents()) {
            if (item.getItemMeta() instanceof LeatherArmorMeta) {
                LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
                meta.setColor(color);
                item.setItemMeta(meta);
            }
        }
    }

    public String getName() {
        return name().substring(0, 1) + name().substring(1).toLowerCase();
    }

    public boolean isOwnedBy(Player p) {
        return p.isOp()
                || this == CIVILIAN
                || p.hasPermission("anni.class."
                        + getName().toLowerCase());
    }

    public void addScoutParticles(Player p) {
        if (this != SCOUT)
            return;
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
                Integer.MAX_VALUE, 0, true), true);
    }

    public ItemStack getIcon() {
        return icon;
    }
}