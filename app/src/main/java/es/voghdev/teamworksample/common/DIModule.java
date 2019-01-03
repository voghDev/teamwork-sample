package es.voghdev.teamworksample.common;

import es.voghdev.teamworksample.BuildConfig;
import es.voghdev.teamworksample.features.projects.ProjectRepository;
import es.voghdev.teamworksample.features.projects.api.GetProjectsApiDataSource;

public class DIModule {
    static ProjectRepository projectRepository;

    public static ProjectRepository getProjectRepository() {
        if (null == projectRepository)
            projectRepository = new ProjectRepository(new GetProjectsApiDataSource(BuildConfig.TeamWorkApiToken));

        return projectRepository;
    }
}
