package io.github.anjoismysign.blobbuild.director.manager;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.block.BlockType;
import org.bukkit.configuration.file.FileConfiguration;
import io.github.anjoismysign.blobbuild.BlobBuild;
import io.github.anjoismysign.blobbuild.director.BuildManager;
import io.github.anjoismysign.blobbuild.director.BuildManagerDirector;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ConfigManager extends BuildManager {
    private FileConfiguration configuration;
    private boolean antiArmorStandDestroy;
    private boolean antiItemFrameDestroy;
    private boolean antiItemFrameInteract;
    private boolean antiCropTrample;
    private boolean antiBlockInteract;
    private Set<BlockType> allowedBlockInteract;

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
        antiBlockInteract = configuration
                .getBoolean("AntiBlock-Interact.Register");
        Registry<BlockType> blockTypes = RegistryAccess.registryAccess().getRegistry(RegistryKey.BLOCK);
        allowedBlockInteract = configuration
                .getStringList("AntiBlock-Interact.Allowed")
                .stream()
                .map(key-> {
                    String[] split = key.split(":");
                    if (split.length == 1){
                        return blockTypes.get(NamespacedKey.minecraft(key));
                    } else {
                        return blockTypes.get(new NamespacedKey(split[0], split[1]));
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableSet());
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

    public boolean antiBlockInteract(){
        return antiBlockInteract;
    }

    public Set<BlockType> getAllowedBlockInteract() {
        return allowedBlockInteract;
    }
}