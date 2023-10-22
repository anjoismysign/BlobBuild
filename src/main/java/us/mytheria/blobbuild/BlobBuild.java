package us.mytheria.blobbuild;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import us.mytheria.blobbuild.director.BuildManagerDirector;
import us.mytheria.bloblib.entities.PluginUpdater;
import us.mytheria.bloblib.managers.BlobPlugin;
import us.mytheria.bloblib.managers.IManagerDirector;

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