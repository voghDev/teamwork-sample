package es.voghdev.teamworksample.features.projects.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import es.voghdev.teamworksample.R;
import es.voghdev.teamworksample.common.DIModule;
import es.voghdev.teamworksample.common.NullInitialData;
import es.voghdev.teamworksample.common.ui.BaseActivity;
import es.voghdev.teamworksample.features.projects.Project;
import es.voghdev.teamworksample.features.projects.ProjectRepository;
import es.voghdev.teamworksample.features.projects.ui.presenter.MainPresenter;
import es.voghdev.teamworksample.features.projects.ui.renderer.ProjectRenderer;
import es.voghdev.teamworksample.features.projects.ui.renderer.ProjectRow;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static es.voghdev.teamworksample.features.projects.ui.activity.ProjectDetailActivity.EXTRA_PROJECT_ID;

public class MainActivity extends BaseActivity implements MainPresenter.MVPView, MainPresenter.Navigator, ProjectRenderer.OnRowClicked {

    @BindView(R.id.tvEmptyCase)
    TextView tvEmptyCase;

    MainPresenter presenter;

    ProjectRepository projectRepository;

    RVRendererAdapter<ProjectRow> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        projectRepository = DIModule.getProjectRepository();

        presenter = new MainPresenter(projectRepository);
        presenter.setView(this);
        presenter.setNavigator(this);

        presenter.initialize(new NullInitialData());
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
    public void addProject(Project project, int index) {
        adapter.add(new ProjectRow(project, index));

        adapter.notifyDataSetChanged();
    }

    @Override
    public void configureProjectsGrid() {
        ProjectRenderer renderer = new ProjectRenderer(this);
        RendererBuilder<ProjectRow> rendererBuilder = new RendererBuilder<ProjectRow>()
                .bind(ProjectRow.class, renderer);
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
    public void showEmptyCase() {
        tvEmptyCase.setVisibility(VISIBLE);
    }

    @Override
    public void hideEmptyCase() {
        tvEmptyCase.setVisibility(INVISIBLE);
    }

    @Override
    public void openProjectDetailScreen(Project project) {
        Intent intent = new Intent(this, ProjectDetailActivity.class);
        intent.putExtra(EXTRA_PROJECT_ID, project.getId());
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
