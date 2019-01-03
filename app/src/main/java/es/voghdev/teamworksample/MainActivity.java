package es.voghdev.teamworksample;

import android.os.Bundle;

import es.voghdev.teamworksample.common.DIModule;
import es.voghdev.teamworksample.common.ui.BaseActivity;
import es.voghdev.teamworksample.features.projects.ProjectRepository;
import es.voghdev.teamworksample.features.projects.ui.presenter.MainPresenter;

public class MainActivity extends BaseActivity implements MainPresenter.MVPView, MainPresenter.Navigator {

    MainPresenter presenter;

    ProjectRepository projectRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        projectRepository = DIModule.getProjectRepository();

        presenter = new MainPresenter(projectRepository);
        presenter.setView(this);
        presenter.setNavigator(this);

        presenter.initialize();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
