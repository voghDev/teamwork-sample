package es.voghdev.teamworksample.features.projects.ui.presenter;

import es.voghdev.teamworksample.common.Presenter;
import es.voghdev.teamworksample.features.projects.GetProjectById;
import es.voghdev.teamworksample.features.projects.Project;
import es.voghdev.teamworksample.features.projects.ProjectRepository;

public class ProjectDetailPresenter extends
        Presenter<ProjectDetailPresenter.MVPView, ProjectDetailPresenter.Navigator> {

    ProjectRepository projectRepository;

    public ProjectDetailPresenter(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void initialize(ProjectDetailInitialData data) {
        view.configureToolbarBackButton();

        if (data.containsProjectId()) {
            projectRepository.getProjectById(data.getProjectId(), new GetProjectById.Listener() {
                @Override
                public void onSuccess(Project project) {
                    view.showToolbarTitle(project.getName());

                    view.loadProjectLogo(project.getLogo());

                    view.showProjectStatus(capitalize(project.getStatus()));
                    view.showProjectSubStatus(capitalize(project.getSubStatus()));

                    if (project.isProjectAdmin()) {
                        view.showProjectAdminLabel();
                    }

                    if (project.isStarred()) {
                        view.showFilledStar();
                    } else {
                        view.showEmptyStar();
                    }

                    view.showProjectDescription(project.getDescription());

                    if (project.getCategory().hasName()) {
                        view.showProjectCategory(capitalize(project.getCategory().getName()));
                    }
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

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        return text.substring(0, 1).toUpperCase().concat(text.substring(1));
    }

    public void onBackButtonClicked() {
        navigator.close();
    }

    public interface ProjectDetailInitialData extends InitialData {
        boolean containsProjectId();

        String getProjectId();
    }

    public interface MVPView {

        void showToolbarTitle(String name);

        void configureToolbarBackButton();

        void loadProjectLogo(String logo);

        void showProjectStatus(String status);

        void showProjectSubStatus(String subStatus);

        void showProjectAdminLabel();

        void showFilledStar();

        void showEmptyStar();

        void showProjectDescription(String description);

        void showProjectCategory(String category);
    }

    public interface Navigator {

        void close();
    }
}