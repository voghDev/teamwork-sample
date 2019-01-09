package es.voghdev.teamworksample.features.projects.ui.renderer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pedrogomez.renderers.Renderer
import es.voghdev.teamworksample.R
import es.voghdev.teamworksample.features.projects.Project

class ProjectRenderer(val listener: OnRowClicked?) : Renderer<ProjectRow>() {
    var tvTitle: TextView? = null
    var tvDescription: TextView? = null
    var tvStatus: TextView? = null

    override fun inflate(inflater: LayoutInflater?, parent: ViewGroup?): View =
        inflater?.inflate(R.layout.row_project, parent, false) ?: View(context)

    override fun setUpView(rootView: View?) {
        tvTitle = rootView?.findViewById(R.id.tvTitle)
        tvDescription = rootView?.findViewById(R.id.tvDescription)
        tvStatus = rootView?.findViewById(R.id.tvStatus)
    }

    override fun hookListeners(rootView: View?) {
        rootView?.setOnClickListener {
            listener?.onProjectClicked(content.project)
        }
    }

    override fun render() {
        renderTitle(content.project)
        renderDescription(content.project)
        renderBackground(content.rowIndex)
        renderStatus(content.project)
    }

    private fun renderTitle(project: Project) {
        tvTitle?.text = project.name
    }

    private fun renderDescription(project: Project) {
        tvDescription?.text = project.description
    }

    private fun renderBackground(rowIndex: Int) {
        val backgroundResId = when (rowIndex % 4) {
            0 -> R.drawable.ripple_blue_darker
            1 -> R.drawable.ripple_green
            2 -> R.drawable.ripple_pink
            else -> R.drawable.ripple_blue
        }

        rootView.setBackgroundResource(backgroundResId)
    }

    private fun renderStatus(project: Project) {
        tvStatus?.text = project.status.capitalize()
    }

    interface OnRowClicked {
        fun onProjectClicked(project: Project)
    }
}
