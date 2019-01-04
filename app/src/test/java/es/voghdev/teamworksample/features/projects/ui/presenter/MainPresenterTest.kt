package es.voghdev.teamworksample.features.projects.ui.presenter

import com.nhaarman.mockito_kotlin.*
import es.voghdev.teamworksample.features.projects.GetProjects
import es.voghdev.teamworksample.features.projects.Project
import es.voghdev.teamworksample.features.projects.ProjectRepository
import io.kotlintest.specs.StringSpec

class MainPresenterTest : StringSpec({
    val mockProjectRepository: ProjectRepository = mock()

    val mockView: MainPresenter.MVPView = mock()

    val mockNavigator: MainPresenter.Navigator = mock()

    val presenter = MainPresenter(mockProjectRepository).apply {
        setView(mockView)
        setNavigator(mockNavigator)
    }

    "should show the empty case if projects list is empty" {
        givenTheApiReturnsNoProjects(mockProjectRepository)

        presenter.initialize()

        verify(mockView).showEmptyCase()
    }
})

private fun givenTheApiReturnsNoProjects(mockProjectRepository: ProjectRepository) {
    givenTheApiReturnsSomeProjects(mockProjectRepository, emptyList())
}

private fun givenTheApiReturnsSomeProjects(repository: ProjectRepository, projects: List<Project>) {
    doAnswer {
        val callback = it.arguments[0] as GetProjects.Listener

        callback.onSuccess(projects)
    }.whenever(repository).getProjects(any())
}