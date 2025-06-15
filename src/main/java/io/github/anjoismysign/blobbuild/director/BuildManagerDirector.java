package io.github.anjoismysign.blobbuild.director;

import io.github.anjoismysign.blobbuild.BlobBuild;
import io.github.anjoismysign.blobbuild.director.manager.ConfigManager;
import io.github.anjoismysign.blobbuild.listener.ListenerManager;
import io.github.anjoismysign.bloblib.entities.GenericManagerDirector;

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