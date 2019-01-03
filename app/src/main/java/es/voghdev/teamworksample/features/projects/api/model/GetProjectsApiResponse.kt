package es.voghdev.teamworksample.features.projects.api.model

import es.voghdev.teamworksample.features.projects.api.model.ProjectApiEntry

class GetProjectsApiResponse(
        val STATUS: String?,
        val projects: List<ProjectApiEntry>?
)
