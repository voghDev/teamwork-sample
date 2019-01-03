package es.voghdev.teamworksample.features.projects;

import java.util.List;

public class ProjectRepository {
    GetProjects getProjectsApiDataSource;

    public ProjectRepository(GetProjects getProjectsApiDataSource) {
        this.getProjectsApiDataSource = getProjectsApiDataSource;
    }

    public void getProjects(final GetProjects.Listener listener) {
        getProjectsApiDataSource.getProjects(new GetProjects.Listener() {
            @Override
            public void onSuccess(List<Project> projects) {
                listener.onSuccess(projects);
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}
