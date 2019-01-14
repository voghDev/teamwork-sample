package es.voghdev.teamworksample.features.projects

class Project(
    val id: String,
    val name: String,
    val description: String,
    val logo: String,
    val status: String,
    val subStatus: String,
    val isProjectAdmin: Boolean,
    val isStarred: Boolean,
    val category: ProjectCategory
) {
    fun hasLogo() = logo.isNotEmpty()
}