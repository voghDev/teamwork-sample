package es.voghdev.teamworksample.features.projects;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    GetProjects getProjectsApiDataSource;
    List<Project> projectsCache = new ArrayList<>();

    public ProjectRepository(GetProjects getProjectsApiDataSource) {
        this.getProjectsApiDataSource = getProjectsApiDataSource;
    }

    public void getProjects(final GetProjects.Listener listener) {
        getProjectsApiDataSource.getProjects(new GetProjects.Listener() {
            @Override
            public void onSuccess(List<Project> projects) {
                projectsCache.addAll(projects);

                listener.onSuccess(projects);
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    public void getProjectById(String projectId, final GetProjectById.Listener listener) {
        boolean found = false;

        for (Project project : projectsCache) {
            if (projectId.equals(project.getId())) {
                found = true;
                listener.onSuccess(project);
            }
        }

        if (!found) {
            listener.onFailure(new Exception(String.format("Project %s not found", projectId)));
        }
    }
}
