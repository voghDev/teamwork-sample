package es.voghdev.teamworksample.features.projects.api;

public class GetProjectsApiDataSource implements GetProjects {
    String apiToken;

    public GetProjectsApiDataSource(String apiToken) {
        this.apiToken = apiToken;
    }

    @Override
    public void getProjects(Listener listener) {

    }
}
