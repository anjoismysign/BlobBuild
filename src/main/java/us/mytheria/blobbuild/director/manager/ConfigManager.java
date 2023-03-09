package us.mytheria.blobbuild.director.manager;

import org.bukkit.configuration.file.FileConfiguration;
import us.mytheria.bloblib.entities.SimpleEventListener;
import us.mytheria.blobbuild.director.BuildManager;
import us.mytheria.blobbuild.director.BuildManagerDirector;
import us.mytheria.blobbuild.BlobBuild;

import java.util.List;

public class ConfigManager extends BuildManager {
    private FileConfiguration configuration;
    private boolean antiArmorStandDestroy;

    public ConfigManager(BuildManagerDirector managerDirector) {
        super(managerDirector);
        reload();
    }

    @Override
    public void reload(){
        BlobBuild main = BlobBuild.getInstance();
        main.reloadConfig();
        main.saveDefaultConfig();
        main.getConfig().options().copyDefaults(true);
        main.saveConfig();
        configuration = main.getConfig();
        antiArmorStandDestroy = configuration.getBoolean("AntiArmorStand-Destroy.Register");
    }

    @Override
    public void loadInConstructor() {
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public boolean antiArmorStandDestroy() {
        return antiArmorStandDestroy;
    }
}