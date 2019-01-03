package es.voghdev.teamworksample.features.projects.ui.presenter;

import android.util.Log;

import java.util.List;

import es.voghdev.teamworksample.common.Presenter;
import es.voghdev.teamworksample.features.projects.Project;
import es.voghdev.teamworksample.features.projects.ProjectRepository;
import es.voghdev.teamworksample.features.projects.GetProjects;

public class MainPresenter extends Presenter<MainPresenter.MVPView, MainPresenter.Navigator> {

    ProjectRepository projectRepository;

    public MainPresenter(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void initialize() {
        projectRepository.getProjects(new GetProjects.Listener() {
            @Override
            public void onSuccess(List<Project> projects) {
                Log.d("ok", "ok");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("fail", "fail");

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

    public interface MVPView {

    }

    public interface Navigator {

    }
}
