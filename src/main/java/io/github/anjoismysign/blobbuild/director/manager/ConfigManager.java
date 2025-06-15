package io.github.anjoismysign.blobbuild.director.manager;

import org.bukkit.configuration.file.FileConfiguration;
import io.github.anjoismysign.blobbuild.BlobBuild;
import io.github.anjoismysign.blobbuild.director.BuildManager;
import io.github.anjoismysign.blobbuild.director.BuildManagerDirector;

public class ConfigManager extends BuildManager {
    private FileConfiguration configuration;
    private boolean antiArmorStandDestroy;
    private boolean antiItemFrameDestroy;
    private boolean antiItemFrameInteract;
    private boolean antiCropTrample;

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
        antiArmorStandDestroy = configuration
                .getBoolean("AntiArmorStand-Destroy.Register");
        antiItemFrameDestroy = configuration
                .getBoolean("AntiItemFrame-Destroy.Register");
        antiItemFrameInteract = configuration
                .getBoolean("AntiItemFrame-Interact.Register");
        antiCropTrample = configuration
                .getBoolean("AntiCrop-Trample.Register");
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public boolean antiArmorStandDestroy() {
        return antiArmorStandDestroy;
    }

    public boolean antiItemFrameDestroy() {
        return antiItemFrameDestroy;
    }

    public boolean antiItemFrameInteract() {
        return antiItemFrameInteract;
    }

    public boolean antiCropTrample() {
        return antiCropTrample;
    }
}