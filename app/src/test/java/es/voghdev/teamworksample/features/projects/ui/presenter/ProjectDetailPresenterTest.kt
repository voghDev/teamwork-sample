package es.voghdev.teamworksample.features.projects.ui.presenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import es.voghdev.teamworksample.features.projects.GetProjectById
import es.voghdev.teamworksample.features.projects.Project
import es.voghdev.teamworksample.features.projects.ProjectCategory
import es.voghdev.teamworksample.features.projects.ProjectRepository
import io.kotlintest.specs.StringSpec

class ProjectDetailPresenterTest : StringSpec({
    val mockProjectRepository: ProjectRepository = mock()

    val mockView: ProjectDetailPresenter.MVPView = mock()

    val mockNavigator: ProjectDetailPresenter.Navigator = mock()

    val presenter = ProjectDetailPresenter(mockProjectRepository).apply {
        setView(mockView)
        setNavigator(mockNavigator)
    }

    val aProject = Project(
        id = "462923",
        name = "Wildlife in amazonia",
        description = "The Amazon River Dolphin looks remarkably different from its more familiar, ocean-faring cousin. Capybara",
        logo = "http://www.example.com/logo.png",
        status = "active",
        subStatus = "late",
        isProjectAdmin = true,
        isStarred = true,
        category = ProjectCategory("269", "mobile", "00a2ff")
    )

    "should load project logo if a valid project id is passed in Initial Data" {
        givenThereIsAProject(mockProjectRepository, aProject)

        presenter.initialize(object : ProjectDetailPresenter.ProjectDetailInitialData {
            override fun containsProjectId() = true
            override fun getProjectId() = "462923"
        })

        verify(mockView).loadProjectLogo("http://www.example.com/logo.png")
    }

    "should show project name as Toolbar title if a valid project id is passed in Initial Data" {
        givenThereIsAProject(mockProjectRepository, aProject)

        presenter.initialize(object : ProjectDetailPresenter.ProjectDetailInitialData {
            override fun containsProjectId() = true
            override fun getProjectId() = "462923"
        })

        verify(mockView).showToolbarTitle("Wildlife in amazonia")
    }

    "should show project description if a valid project id is passed in Initial Data" {
        givenThereIsAProject(mockProjectRepository, aProject)

        presenter.initialize(object : ProjectDetailPresenter.ProjectDetailInitialData {
            override fun containsProjectId() = true
            override fun getProjectId() = "462923"
        })

        verify(mockView).showProjectDescription("The Amazon River Dolphin looks remarkably different from its more familiar, ocean-faring cousin. Capybara")
    }

    "should close the screen when user taps back button" {
        presenter.initialize(object : ProjectDetailPresenter.ProjectDetailInitialData {
            override fun containsProjectId(): Boolean = true
            override fun getProjectId(): String = "462923"
        })

        presenter.onBackButtonClicked()

        verify(mockNavigator).close()
    }
})

fun givenThereIsAProject(repository: ProjectRepository, project: Project) {
    doAnswer {
        val callback = it.arguments[1] as GetProjectById.Listener

        callback.onSuccess(project)
    }.whenever(repository).getProjectById(any(), any())
}