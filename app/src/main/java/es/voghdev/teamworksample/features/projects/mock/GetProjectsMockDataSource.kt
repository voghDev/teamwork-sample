package es.voghdev.teamworksample.features.projects.mock

import es.voghdev.teamworksample.features.projects.GetProjects
import es.voghdev.teamworksample.features.projects.Project

class GetProjectsMockDataSource : GetProjects {
    override fun getProjects(listener: GetProjects.Listener?) {
        val names = listOf(
            "Wildlife in amazonia",
            "Stem cell R&D",
            "Canada Manufacturers",
            "Flagship mother of pearl stainless",
            "Underwater biology research",
            "Ice in the sky",
            "Moon & Mars"
        )

        val descriptions = listOf(
            "The Amazon River Dolphin looks remarkably different from its more familiar, ocean-faring cousin. Capybara.",
            "an undifferentiated cell of a multicellular organism which is capable of giving rise to indefinitely more cells of the same type",
            "Canada's main manufacturing industries range from paper, aerospace, automobile, machinery, food to clothing",
            "26 mm silver tone stainless steel case with a diamond-studded bezel",
            "Marine biology is the scientific study of marine life, organisms in the sea. Given that in biology many phyla, families and genera",
            "Ice and the Sky is a 2015 French documentary film directed by Luc Jacquet about the work of Claude Lorius",
            "You'll have no trouble seeing the red planet Mars with the eye alone. But Mars' two dinky moons – Phobos and Deimos"
        )

        listener?.onSuccess(names.mapIndexed { i, name ->
            Project(
                id = "00${i + 1}",
                name = name,
                description = descriptions[i],
                logo = "https://s3.amazonaws.com/TWFiles/349705/projectLogo/tf_2E2AD316-DAF2-C47C-BA4D4BCE9184E395.Fg204.jpg",
                status = "active",
                subStatus = "late",
                isProjectAdmin = false,
                isStarred = false
            )
        })
    }
}
