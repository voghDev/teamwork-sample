package es.voghdev.teamworksample.features.projects.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import org.jetbrains.annotations.NotNull;

import es.voghdev.teamworksample.R;
import es.voghdev.teamworksample.common.DIModule;
import es.voghdev.teamworksample.common.ui.BaseActivity;
import es.voghdev.teamworksample.features.projects.Project;
import es.voghdev.teamworksample.features.projects.ProjectRepository;
import es.voghdev.teamworksample.features.projects.ui.presenter.MainPresenter;
import es.voghdev.teamworksample.features.projects.ui.renderer.ProjectRenderer;

public class MainActivity extends BaseActivity implements MainPresenter.MVPView, MainPresenter.Navigator, ProjectRenderer.OnRowClicked {

    MainPresenter presenter;

    ProjectRepository projectRepository;

    RVRendererAdapter<Project> adapter;

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

    @Override
    public void clearList() {
        adapter.clear();
    }

    @Override
    public void addProject(Project project) {
        adapter.add(project);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void configureProjectsGrid() {
        ProjectRenderer renderer = new ProjectRenderer(this);
        RendererBuilder<Project> rendererBuilder = new RendererBuilder<Project>()
                .bind(Project.class, renderer);
        adapter = new RVRendererAdapter<>(rendererBuilder);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onProjectClicked(@NotNull Project project) {
        presenter.onProjectClicked(project);
    }

    @Override
    public void openProjectDetailScreen(Project project) {

    }
}
