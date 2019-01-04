package es.voghdev.teamworksample.features.projects.ui.activity;

import android.os.Bundle;

import es.voghdev.teamworksample.R;
import es.voghdev.teamworksample.common.DIModule;
import es.voghdev.teamworksample.common.ui.BaseActivity;
import es.voghdev.teamworksample.features.projects.ProjectRepository;
import es.voghdev.teamworksample.features.projects.ui.presenter.ProjectDetailPresenter;

public class ProjectDetailActivity extends BaseActivity implements ProjectDetailPresenter.MVPView, ProjectDetailPresenter.Navigator {

    ProjectDetailPresenter presenter;

    ProjectRepository projectRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        projectRepository = DIModule.getProjectRepository();

        presenter = new ProjectDetailPresenter(projectRepository);
        presenter.setView(this);
        presenter.setNavigator(this);

        presenter.initialize();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_detail;
    }
}
