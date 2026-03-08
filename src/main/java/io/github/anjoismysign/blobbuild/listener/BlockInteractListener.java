package io.github.anjoismysign.blobbuild.listener;

import io.github.anjoismysign.blobbuild.director.manager.ConfigManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockInteractListener implements Listener {
    private final ListenerManager listenerManager;
    private final ConfigManager configManager;

    public BlockInteractListener(ListenerManager listenerManager) {
        this.listenerManager = listenerManager;
        this.configManager = listenerManager.getManagerDirector().getConfigManager();
    }

    public void unload() {
        HandlerList.unregisterAll(this);
    }

    public void load() {
        if (configManager.antiBlockInteract())
            listenerManager.getPlugin().getServer().getPluginManager().registerEvents(this, listenerManager.getPlugin());
    }

    public void reload() {
        unload();
        load();
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }
        Player player = event.getPlayer();
        if (listenerManager.isWhitelisted(player))
            return;
        if (listenerManager.exception.contains(player.getName()))
            return;
        if (configManager.getAllowedBlockInteract().contains(event.getClickedBlock().getType().asBlockType())){
            return;
        }
        event.setCancelled(true);
    }
}
