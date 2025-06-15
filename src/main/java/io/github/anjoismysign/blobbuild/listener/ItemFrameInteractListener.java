package io.github.anjoismysign.blobbuild.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import io.github.anjoismysign.blobbuild.director.manager.ConfigManager;

public class ItemFrameInteractListener implements Listener {
    private final ListenerManager listenerManager;
    private final ConfigManager configManager;

    public ItemFrameInteractListener(ListenerManager listenerManager) {
        this.listenerManager = listenerManager;
        this.configManager = listenerManager.getManagerDirector().getConfigManager();
    }

    public void unload() {
        HandlerList.unregisterAll(this);
    }

    public void load() {
        if (configManager.antiItemFrameInteract())
            listenerManager.getPlugin().getServer().getPluginManager().registerEvents(this, listenerManager.getPlugin());
    }

    public void reload() {
        unload();
        load();
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() != EntityType.ITEM_FRAME)
            return;
        event.setCancelled(true);
    }
}
