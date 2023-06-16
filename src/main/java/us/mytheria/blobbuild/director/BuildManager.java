package us.mytheria.blobbuild.director;

import us.mytheria.blobbuild.BlobBuild;
import us.mytheria.bloblib.entities.GenericManager;

public class BuildManager extends GenericManager<BlobBuild, BuildManagerDirector> {

    public BuildManager(BuildManagerDirector managerDirector) {
        super(managerDirector);
    }
}