package io.github.anjoismysign.blobbuild;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import io.github.anjoismysign.blobbuild.director.BuildManagerDirector;
import io.github.anjoismysign.bloblib.entities.PluginUpdater;
import io.github.anjoismysign.bloblib.managers.BlobPlugin;
import io.github.anjoismysign.bloblib.managers.IManagerDirector;

public class BlobBuild extends BlobPlugin {
    private BuildManagerDirector director;
    private IManagerDirector proxy;
    private PluginUpdater updater;

    @Override
    public void onEnable() {
        director = new BuildManagerDirector(this);
        proxy = director.proxy();
        updater = generateGitHubUpdater("anjoismysign", "BlobBuild");
        Bukkit.getScheduler().runTask(this, () ->
                director.postWorld());
    }

    public IManagerDirector getManagerDirector() {
        return proxy;
    }

    @Override
    @NotNull
    public PluginUpdater getPluginUpdater() {
        return updater;
    }
}