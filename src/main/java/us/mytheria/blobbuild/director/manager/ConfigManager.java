package us.mytheria.blobbuild.director.manager;

import org.bukkit.configuration.file.FileConfiguration;
import us.mytheria.bloblib.entities.SimpleEventListener;
import us.mytheria.blobbuild.director.BuildManager;
import us.mytheria.blobbuild.director.BuildManagerDirector;
import us.mytheria.blobbuild.BlobBuild;

import java.util.List;

public class ConfigManager extends BuildManager {
    private FileConfiguration configuration;

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
    }

    @Override
    public void loadInConstructor() {
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }
}