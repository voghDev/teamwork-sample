package es.voghdev.teamworksample.features.projects.mock

import es.voghdev.teamworksample.features.projects.GetProjects
import es.voghdev.teamworksample.features.projects.Project

class GetProjectsMockDataSource : GetProjects {
    override fun getProjects(listener: GetProjects.Listener?) {
        val projectNames = listOf(
                "Wildlife in amazonia",
                "Stem cell R&D",
                "Canada Manufacturers",
                "Flagship mother of pearl stainless",
                "Underwater biology research",
                "Ice in the sky",
                "Moon & Mars"
        )

        listener?.onSuccess(projectNames.mapIndexed { i, name ->
            Project(id = "00${i + 1}", name = name, status = "active")
        })
    }
}
