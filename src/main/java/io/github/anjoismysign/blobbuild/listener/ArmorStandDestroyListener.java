package io.github.anjoismysign.blobbuild.listener;

import io.github.anjoismysign.blobbuild.director.manager.ConfigManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ArmorStandDestroyListener implements Listener {
    private final ListenerManager listenerManager;
    private final ConfigManager configManager;

    public ArmorStandDestroyListener(ListenerManager listenerManager) {
        this.listenerManager = listenerManager;
        this.configManager = listenerManager.getManagerDirector().getConfigManager();
    }

    public void unload() {
        HandlerList.unregisterAll(this);
    }

    public void load() {
        if (configManager.antiArmorStandDestroy())
            listenerManager.getPlugin().getServer().getPluginManager().registerEvents(this, listenerManager.getPlugin());
    }

    public void reload() {
        unload();
        load();
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() != EntityType.ARMOR_STAND) {
            return;
        }
        Entity damager = event.getDamager();
        if (damager.getType() != EntityType.PLAYER){
            return;
        }
        Player player = (Player) damager;
        if (listenerManager.isWhitelisted(player)) {
            return;
        }
        if (listenerManager.exception.contains(player.getName())) {
            return;
        }
        event.setCancelled(true);
    }
}
