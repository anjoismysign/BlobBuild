package io.github.anjoismysign.blobbuild.listener;

import io.github.anjoismysign.blobbuild.director.manager.ConfigManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;

public class ItemFrameDestroyListener implements Listener {
    private final ListenerManager listenerManager;
    private final ConfigManager configManager;

    public ItemFrameDestroyListener(ListenerManager listenerManager) {
        this.listenerManager = listenerManager;
        this.configManager = listenerManager.getManagerDirector().getConfigManager();
    }

    public void unload() {
        HandlerList.unregisterAll(this);
    }

    public void load() {
        if (configManager.antiItemFrameDestroy())
            listenerManager.getPlugin().getServer().getPluginManager().registerEvents(this, listenerManager.getPlugin());
    }

    public void reload() {
        unload();
        load();
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onDamage(HangingBreakByEntityEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() != EntityType.ITEM_FRAME) {
            return;
        }
        Entity damager = event.getRemover();
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
