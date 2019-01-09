package es.voghdev.teamworksample.features.projects.ui.presenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import es.voghdev.teamworksample.common.Presenter
import es.voghdev.teamworksample.features.projects.GetProjects
import es.voghdev.teamworksample.features.projects.Project
import es.voghdev.teamworksample.features.projects.ProjectCategory
import es.voghdev.teamworksample.features.projects.ProjectRepository
import io.kotlintest.specs.StringSpec
import org.mockito.ArgumentMatchers.anyInt

class MainPresenterTest : StringSpec({
    val emptyInitialData = object : Presenter.InitialData {}

    val mockProjectRepository: ProjectRepository = mock()

    val mockView: MainPresenter.MVPView = mock()

    val mockNavigator: MainPresenter.Navigator = mock()

    val presenter = MainPresenter(mockProjectRepository).apply {
        setView(mockView)
        setNavigator(mockNavigator)
    }

    val someProjects = (1..4).map { i ->
        Project(
            "00$i",
            "Project #00$i",
            "This is a test project - $i",
            "",
            "active",
            "late",
            false,
            false,
            ProjectCategory()
        )
    }

    "should configure the projects grid on start" {
        presenter.initialize(emptyInitialData)

        verify(mockView).configureProjectsGrid()
    }

    "should request user projects to the Api on start" {
        presenter.initialize(emptyInitialData)

        verify(mockProjectRepository).getProjects(any())
    }

    "should add four cells to the grid if the Api returns four projects" {
        givenTheApiReturnsSomeProjects(mockProjectRepository, someProjects)

        presenter.initialize(emptyInitialData)

        verify(mockView, times(4)).addProject(any(), anyInt())
    }

    "should not show the empty case if the Api returns one or more projects" {
        givenTheApiReturnsSomeProjects(mockProjectRepository, someProjects)

        presenter.initialize(emptyInitialData)

        verify(mockView, never()).showEmptyCase()
    }

    "should show the empty case if projects list is empty" {
        givenTheApiReturnsNoProjects(mockProjectRepository)

        presenter.initialize(emptyInitialData)

        verify(mockView).showEmptyCase()
    }
})

fun givenTheApiReturnsNoProjects(mockProjectRepository: ProjectRepository) {
    givenTheApiReturnsSomeProjects(mockProjectRepository, emptyList())
}

fun givenTheApiReturnsSomeProjects(repository: ProjectRepository, projects: List<Project>) {
    doAnswer {
        val callback = it.arguments[0] as GetProjects.Listener

        callback.onSuccess(projects)
    }.whenever(repository).getProjects(any())
}
