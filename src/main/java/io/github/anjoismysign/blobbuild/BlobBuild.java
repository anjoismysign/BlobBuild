package io.github.anjoismysign.blobbuild;

import io.github.anjoismysign.blobbuild.director.BuildManagerDirector;
import io.github.anjoismysign.bloblib.manager.BlobPlugin;
import io.github.anjoismysign.bloblib.manager.IManagerDirector;
import io.github.anjoismysign.bloblib.updater.PluginUpdater;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

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