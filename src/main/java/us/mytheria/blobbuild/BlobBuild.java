package us.mytheria.blobbuild;

import org.bukkit.Bukkit;
import us.mytheria.bloblib.managers.BlobPlugin;
import us.mytheria.blobbuild.director.BuildManagerDirector;
import us.mytheria.bloblib.managers.IManagerDirector;

public class BlobBuild extends BlobPlugin {
    private BuildManagerDirector director;
    private IManagerDirector proxy;

    public static BlobBuild INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        director = new BuildManagerDirector(this);
        proxy = director.proxy();
        Bukkit.getScheduler().runTask(this, () ->
                director.postWorld());
    }

    @Override
    public void onDisable() {
        unregisterFromBlobLib();
        director.unload();
    }

    public IManagerDirector getManagerDirector() {
        return proxy;
    }
}