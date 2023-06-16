package us.mytheria.blobbuild.director.manager;

import org.bukkit.configuration.file.FileConfiguration;
import us.mytheria.blobbuild.BlobBuild;
import us.mytheria.blobbuild.director.BuildManager;
import us.mytheria.blobbuild.director.BuildManagerDirector;

public class ConfigManager extends BuildManager {
    private FileConfiguration configuration;
    private boolean antiArmorStandDestroy;

    public ConfigManager(BuildManagerDirector managerDirector) {
        super(managerDirector);
        reload();
    }

    @Override
    public void reload() {
        BlobBuild plugin = getPlugin();
        plugin.reloadConfig();
        plugin.saveDefaultConfig();
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
        configuration = plugin.getConfig();
        antiArmorStandDestroy = configuration.getBoolean("AntiArmorStand-Destroy.Register");
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public boolean antiArmorStandDestroy() {
        return antiArmorStandDestroy;
    }
}