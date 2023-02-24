package us.mytheria.blobbuild.director;

import us.mytheria.bloblib.managers.ManagerDirector;
import us.mytheria.blobbuild.BlobBuild;
import us.mytheria.blobbuild.director.manager.ConfigManager;
import us.mytheria.blobbuild.director.manager.listener.ListenerManager;

public class BuildManagerDirector extends ManagerDirector {
    public static BuildManagerDirector getInstance() {
        return BlobBuild.getInstance().getManagerDirector();
    }

    public BuildManagerDirector() {
        super(BlobBuild.getInstance());
        addManager("ConfigManager", new ConfigManager(this));
        addManager("ListenerManager", new ListenerManager(this));
    }

    /**
     * From top to bottom, follow the order.
     */
    @Override
    public void reload() {
        getConfigManager().reload();
        getListenerManager().reload();
    }

    @Override
    public void unload() {
    }

    @Override
    public void postWorld() {
    }

    public final ConfigManager getConfigManager() {
        return (ConfigManager) getManager("ConfigManager");
    }

    public final ListenerManager getListenerManager() {
        return (ListenerManager) getManager("ListenerManager");
    }
}