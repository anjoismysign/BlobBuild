package us.mytheria.blobbuild.listener;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.mytheria.blobbuild.BlobBuild;
import us.mytheria.blobbuild.director.BuildManager;
import us.mytheria.blobbuild.director.BuildManagerDirector;
import us.mytheria.bloblib.api.BlobLibMessageAPI;
import us.mytheria.bloblib.entities.SimpleEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListenerManager extends BuildManager implements Listener, CommandExecutor, TabExecutor {
    protected final Set<String> exception;
    private List<String> whitelist;
    private final BuildListener buildListener;
    private final ArmorStandDestroyListener armorStandListener;
    private final ItemFrameDestroyListener itemFrameListener;
    private final ItemFrameInteractListener itemFrameInteractListener;

    public ListenerManager(BuildManagerDirector managerDirector) {
        super(managerDirector);
        BlobBuild plugin = managerDirector.getPlugin();
        exception = new HashSet<>();
        plugin.getCommand("blobbuild").setExecutor(this);
        plugin.getCommand("blobbuild").setTabCompleter(this);
        buildListener = new BuildListener(this);
        armorStandListener = new ArmorStandDestroyListener(this);
        itemFrameListener = new ItemFrameDestroyListener(this);
        itemFrameInteractListener = new ItemFrameInteractListener(this);
        Bukkit.getPluginManager().registerEvents(this, plugin);
        reload();
    }

    @Override
    public void reload() {
        HandlerList.unregisterAll(buildListener);
        SimpleEventListener<List<String>> whitelist = SimpleEventListener.STRING_LIST
                (getManagerDirector().getConfigManager().getConfiguration()
                        .getConfigurationSection("Build"), "Whitelisted-Players");
        if (whitelist.register()) {
            this.whitelist = whitelist.value();
            Bukkit.getPluginManager().registerEvents(buildListener, getPlugin());
        }
        armorStandListener.reload();
        itemFrameListener.reload();
        itemFrameInteractListener.reload();
    }

    protected boolean isWhitelisted(Player player) {
        return whitelist.contains(player.getName());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        exception.remove(player.getName());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 1)
            return false;
        if (!args[0].equalsIgnoreCase("switchstate"))
            return false;
        if (!(sender instanceof Player player))
            return false;
        if (isWhitelisted(player)) {
            BlobLibMessageAPI.getInstance().getMessage("Switch.Already-Whitelisted", player).handle(player);
            return true;
        }
        if (!player.hasPermission("blobbuild.switchstate"))
            return false;
        if (exception.contains(player.getName())) {
            exception.remove(player.getName());
            BlobLibMessageAPI.getInstance().getMessage("Switch.Denied", player).handle(player);
        } else {
            exception.add(player.getName());
            BlobLibMessageAPI.getInstance().getMessage("Switch.Allowed", player).handle(player);
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 1)
            return new ArrayList<>();
        return List.of("switchstate");
    }
}