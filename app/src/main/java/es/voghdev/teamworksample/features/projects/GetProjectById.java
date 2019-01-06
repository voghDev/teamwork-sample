package es.voghdev.teamworksample.features.projects;

public interface GetProjectById {
    void getProjectById(String projectId, Listener listener);

    interface Listener {
        void onSuccess(Project project);

        void onFailure(Throwable t);
    }
}
