package es.voghdev.teamworksample.features.projects.api.model

import es.voghdev.teamworksample.features.projects.ProjectCategory

class ProjectCategoryApiEntry(
    val id: String,
    val name: String,
    val color: String
) {
    fun toDomain() = ProjectCategory(
        id,
        name,
        color
    )
}
