package es.voghdev.teamworksample.features.projects.api.model

import es.voghdev.teamworksample.features.projects.Project

class ProjectApiEntry(
        val id: String?,
        val name: String?,
        val description: String?,
        val status: String?,
        val subStatus: String?,
        val starred: Boolean?,
        val replyByEmailEnabled: Boolean?
) {
    fun toDomain() = Project(
            id ?: "",
            name ?: "",
            description ?: "",
            status ?: ""
    )
}