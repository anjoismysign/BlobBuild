package io.github.anjoismysign.blobbuild.listener;

import io.github.anjoismysign.blobbuild.director.manager.ConfigManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class ArmorStandInteractListener implements Listener {
    private final ListenerManager listenerManager;
    private final ConfigManager configManager;

    public ArmorStandInteractListener(ListenerManager listenerManager) {
        this.listenerManager = listenerManager;
        this.configManager = listenerManager.getManagerDirector().getConfigManager();
    }

    public void unload() {
        HandlerList.unregisterAll(this);
    }

    public void load() {
        if (configManager.antiArmorStandInteract()) {
            listenerManager.getPlugin().getServer().getPluginManager().registerEvents(this, listenerManager.getPlugin());
        }
    }

    public void reload() {
        unload();
        load();
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onInteract(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (entity.getType() != EntityType.ARMOR_STAND) {
            return;
        }
        Player player = event.getPlayer();
        if (listenerManager.isWhitelisted(player)) {
            return;
        }
        if (listenerManager.exception.contains(player.getName())) {
            return;
        }
        event.setCancelled(true);
    }
}
