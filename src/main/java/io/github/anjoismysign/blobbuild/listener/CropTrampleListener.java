package io.github.anjoismysign.blobbuild.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerInteractEvent;
import io.github.anjoismysign.blobbuild.director.manager.ConfigManager;

public class CropTrampleListener implements Listener {
    private final ListenerManager listenerManager;
    private final ConfigManager configManager;

    public CropTrampleListener(ListenerManager listenerManager) {
        this.listenerManager = listenerManager;
        this.configManager = listenerManager.getManagerDirector().getConfigManager();
    }

    public void unload() {
        HandlerList.unregisterAll(this);
    }

    public void load() {
        if (configManager.antiCropTrample())
            listenerManager.getPlugin().getServer().getPluginManager().registerEvents(this, listenerManager.getPlugin());
    }

    public void reload() {
        unload();
        load();
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTrample(PlayerInteractEvent event) {
        if (event.useInteractedBlock() == Event.Result.DENY)
            return;
        Block block = event.getClickedBlock();
        Material material = block.getType();
        if (material != Material.FARMLAND)
            return;
        event.setCancelled(true);
    }
}
