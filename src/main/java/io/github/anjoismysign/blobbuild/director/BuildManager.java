package io.github.anjoismysign.blobbuild.director;

import io.github.anjoismysign.blobbuild.BlobBuild;
import io.github.anjoismysign.bloblib.entities.GenericManager;

public class BuildManager extends GenericManager<BlobBuild, BuildManagerDirector> {

    public BuildManager(BuildManagerDirector managerDirector) {
        super(managerDirector);
    }
}