package es.voghdev.teamworksample.features.projects.ui.presenter;

import java.util.List;

import es.voghdev.teamworksample.common.Presenter;
import es.voghdev.teamworksample.features.projects.GetProjects;
import es.voghdev.teamworksample.features.projects.Project;
import es.voghdev.teamworksample.features.projects.ProjectRepository;

public class MainPresenter extends Presenter<MainPresenter.MVPView, MainPresenter.Navigator> {

    ProjectRepository projectRepository;

    public MainPresenter(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void initialize(InitialData data) {
        view.configureProjectsGrid();

        projectRepository.getProjects(new GetProjects.Listener() {
            @Override
            public void onSuccess(List<Project> projects) {
                int index = 0;

                view.clearList();

                for (Project project : projects) {
                    view.addProject(project, index++);
                }

                if (projects.isEmpty())
                    view.showEmptyCase();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
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

    public void onProjectClicked(Project project) {
        navigator.openProjectDetailScreen(project);
    }

    public interface MVPView {
        void clearList();

        void addProject(Project project, int index);

        void configureProjectsGrid();

        void showEmptyCase();

        void hideEmptyCase();
    }

    public interface Navigator {
        void openProjectDetailScreen(Project project);
    }
}
