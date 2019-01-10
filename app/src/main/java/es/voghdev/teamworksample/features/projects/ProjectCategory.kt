package es.voghdev.teamworksample.features.projects

data class ProjectCategory(
    val id: String = "",
    val name: String = "",
    val color: String = ""
) {
    fun hasName() = name.isNotEmpty()
}
