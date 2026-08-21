package io.github.anjoismysign.blobbuild.listener;

import io.github.anjoismysign.blobbuild.director.manager.ConfigManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PaintingDestroyListener implements Listener {
    private final ListenerManager listenerManager;
    private final ConfigManager configManager;

    public PaintingDestroyListener(ListenerManager listenerManager) {
        this.listenerManager = listenerManager;
        this.configManager = listenerManager.getManagerDirector().getConfigManager();
    }

    public void unload() {
        HandlerList.unregisterAll(this);
    }

    public void load() {
        if (configManager.antiPaintingDestroy())
            listenerManager.getPlugin().getServer().getPluginManager().registerEvents(this, listenerManager.getPlugin());
    }

    public void reload() {
        unload();
        load();
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() != EntityType.PAINTING){
            return;
            }
        event.setCancelled(true);
    }
}
