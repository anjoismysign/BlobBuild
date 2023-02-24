package us.mytheria.blobbuild;

import org.bukkit.Bukkit;
import us.mytheria.bloblib.managers.BlobPlugin;
import us.mytheria.blobbuild.director.BuildManagerDirector;

public final class BlobBuild extends BlobPlugin {

    private BuildManagerDirector director;

    public static BlobBuild instance;

    public static BlobBuild getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        director = new BuildManagerDirector();
        Bukkit.getScheduler().runTask(this, () ->
                director.postWorld());
    }

    @Override
    public void onDisable() {
        unregisterFromBlobLib();
    }

    @Override
    public BuildManagerDirector getManagerDirector() {
        return director;
    }
}