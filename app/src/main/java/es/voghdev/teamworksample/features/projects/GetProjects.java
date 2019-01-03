package es.voghdev.teamworksample.features.projects;

import java.util.List;

public interface GetProjects {
    void getProjects(Listener listener);

    interface Listener {
        void onSuccess(List<Project> projects);

        void onFailure(Throwable t);
    }
}
