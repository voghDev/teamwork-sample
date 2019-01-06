package es.voghdev.teamworksample.features.projects.ui.presenter;

import es.voghdev.teamworksample.common.Presenter;
import es.voghdev.teamworksample.features.projects.GetProjectById;
import es.voghdev.teamworksample.features.projects.Project;
import es.voghdev.teamworksample.features.projects.ProjectRepository;

public class ProjectDetailPresenter extends Presenter<ProjectDetailPresenter.MVPView, ProjectDetailPresenter.Navigator> {

    ProjectRepository projectRepository;

    public ProjectDetailPresenter(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void initialize(ProjectDetailInitialData data) {
        if (data.containsProjectId()) {
            projectRepository.getProjectById(data.getProjectId(), new GetProjectById.Listener() {

                @Override
                public void onSuccess(Project project) {

                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public interface ProjectDetailInitialData extends InitialData {
        boolean containsProjectId();

        String getProjectId();
    }

    public interface MVPView {

    }

    public interface Navigator {

    }
}