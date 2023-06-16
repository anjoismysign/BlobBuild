package us.mytheria.blobbuild.director;

import us.mytheria.blobbuild.BlobBuild;
import us.mytheria.blobbuild.director.manager.ConfigManager;
import us.mytheria.blobbuild.listener.ListenerManager;
import us.mytheria.bloblib.entities.GenericManagerDirector;

public class BuildManagerDirector extends GenericManagerDirector<BlobBuild> {

    public BuildManagerDirector(BlobBuild plugin) {
        super(plugin);
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