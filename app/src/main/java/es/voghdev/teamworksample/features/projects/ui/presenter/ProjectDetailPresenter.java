package es.voghdev.teamworksample.features.projects.ui.presenter;

import es.voghdev.teamworksample.common.Presenter;
import es.voghdev.teamworksample.features.projects.ProjectRepository;

public class ProjectDetailPresenter extends Presenter<ProjectDetailPresenter.MVPView, ProjectDetailPresenter.Navigator> {

    ProjectRepository projectRepository;

    public ProjectDetailPresenter(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void initialize() {

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

    public interface MVPView {

    }

    public interface Navigator {

    }
}