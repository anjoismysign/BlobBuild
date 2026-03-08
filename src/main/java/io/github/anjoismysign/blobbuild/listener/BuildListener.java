package io.github.anjoismysign.blobbuild.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildListener implements Listener {
    private final ListenerManager listenerManager;

    public BuildListener(ListenerManager listenerManager) {
        this.listenerManager = listenerManager;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (listenerManager.isWhitelisted(player))
            return;
        if (listenerManager.exception.contains(player.getName()))
            return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (listenerManager.isWhitelisted(player))
            return;
        if (listenerManager.exception.contains(player.getName()))
            return;
        event.setCancelled(true);
    }
}
