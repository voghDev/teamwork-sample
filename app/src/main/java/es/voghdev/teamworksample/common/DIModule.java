package es.voghdev.teamworksample.common;

import es.voghdev.teamworksample.features.projects.ProjectRepository;
import es.voghdev.teamworksample.features.projects.mock.GetProjectsMockDataSource;

public class DIModule {
    static ProjectRepository projectRepository;

    public static ProjectRepository getProjectRepository() {
        if (null == projectRepository)
            projectRepository = new ProjectRepository(new GetProjectsMockDataSource()); // new GetProjectsApiDataSource(BuildConfig.TeamWorkApiToken)

        return projectRepository;
    }
}
