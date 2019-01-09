package es.voghdev.teamworksample.features.projects.api.model

import es.voghdev.teamworksample.features.projects.Project
import es.voghdev.teamworksample.features.projects.ProjectCategory

class ProjectApiEntry(
    val id: String?,
    val name: String?,
    val description: String?,
    val logo: String?,
    val status: String?,
    val subStatus: String?,
    val starred: Boolean?,
    val replyByEmailEnabled: Boolean?,
    val isProjectAdmin: Boolean?,
    val isStarred: Boolean?,
    val category: ProjectCategoryApiEntry?
) {
    fun toDomain() = Project(
        id ?: "",
        name ?: "",
        description ?: "",
        logo ?: "",
        status ?: "",
        subStatus ?: "",
        isProjectAdmin ?: false,
        isStarred ?: false,
        category?.toDomain() ?: ProjectCategory()
    )
}