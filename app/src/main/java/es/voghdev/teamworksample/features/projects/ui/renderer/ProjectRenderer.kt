package es.voghdev.teamworksample.features.projects.ui.renderer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pedrogomez.renderers.Renderer
import es.voghdev.teamworksample.R
import es.voghdev.teamworksample.features.projects.Project

class ProjectRenderer(val listener: OnRowClicked?) : Renderer<Project>() {
    var tvTitle: TextView? = null
    var tvDescription: TextView? = null

    override fun inflate(inflater: LayoutInflater?, parent: ViewGroup?): View =
            inflater?.inflate(R.layout.row_project, parent, false) ?: View(context)

    override fun setUpView(rootView: View?) {
        tvTitle = rootView?.findViewById(R.id.tvTitle)
        tvDescription = rootView?.findViewById(R.id.tvDescription)
    }

    override fun hookListeners(rootView: View?) {
        rootView?.setOnClickListener {
            listener?.onProjectClicked(content)
        }
    }

    override fun render() {
        renderTitle(content)
        renderDescription(content)
    }

    private fun renderTitle(project: Project) {
        tvTitle?.text = project.name
    }

    private fun renderDescription(project: Project) {
        tvDescription?.text = project.description
    }

    interface OnRowClicked {
        fun onProjectClicked(project: Project)
    }
}
