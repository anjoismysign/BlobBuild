package us.mytheria.blobbuild.director;

import us.mytheria.bloblib.managers.Manager;

public class BuildManager extends Manager {

    public BuildManager(BuildManagerDirector managerDirector) {
        super(managerDirector);
    }

    @Override
    public BuildManagerDirector getManagerDirector() {
        return (BuildManagerDirector) super.getManagerDirector();
    }
}