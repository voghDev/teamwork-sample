package es.voghdev.teamworksample.features.projects.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import es.voghdev.teamworksample.R;
import es.voghdev.teamworksample.common.DIModule;
import es.voghdev.teamworksample.common.ui.BaseActivity;
import es.voghdev.teamworksample.features.projects.ProjectRepository;
import es.voghdev.teamworksample.features.projects.ui.presenter.ProjectDetailPresenter;

import static android.view.View.VISIBLE;

public class ProjectDetailActivity extends BaseActivity implements
        ProjectDetailPresenter.MVPView, ProjectDetailPresenter.Navigator {

    public static final String EXTRA_PROJECT_ID = "projectId";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.ivProjectImage)
    ImageView ivProjectImage;

    @BindView(R.id.tvProjectStatus)
    TextView tvStatus;

    @BindView(R.id.tvProjectSubStatus)
    TextView tvProjectSubStatus;

    @BindView(R.id.tvIsProjectAdmin)
    TextView tvIsProjectAdmin;

    @BindView(R.id.ivStarred)
    ImageView ivStarred;

    ProjectDetailPresenter presenter;

    ProjectRepository projectRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        projectRepository = DIModule.getProjectRepository();

        presenter = new ProjectDetailPresenter(projectRepository);
        presenter.setView(this);
        presenter.setNavigator(this);

        presenter.initialize(new ProjectDetailPresenter.ProjectDetailInitialData() {
            @Override
            public boolean containsProjectId() {
                return getIntent().getStringExtra(EXTRA_PROJECT_ID) != null
                        && getIntent().getStringExtra(EXTRA_PROJECT_ID).length() > 0;
            }

            @Override
            public String getProjectId() {
                return getIntent().getStringExtra(EXTRA_PROJECT_ID);
            }
        });
    }

    @Override
    public void showToolbarTitle(String name) {
        getSupportActionBar().setTitle(name);
    }

    @Override
    public void configureToolbarBackButton() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            presenter.onBackButtonClicked();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadProjectLogo(String logo) {
        Picasso.get().load(logo).into(ivProjectImage);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void showProjectStatus(String status) {
        tvStatus.setText(status);
    }

    @Override
    public void showProjectSubStatus(String subStatus) {
        tvProjectSubStatus.setText(subStatus);
    }

    @Override
    public void showProjectAdminLabel() {
        tvIsProjectAdmin.setVisibility(VISIBLE);
    }

    @Override
    public void showFilledStar() {
        ivStarred.setImageResource(R.drawable.ic_star);
    }

    @Override
    public void showEmptyStar() {
        ivStarred.setImageResource(R.drawable.ic_star_empty);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_detail;
    }
}
