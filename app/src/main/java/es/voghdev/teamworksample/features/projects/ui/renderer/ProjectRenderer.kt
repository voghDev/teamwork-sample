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
            listener?.onProjectClicked(content)
        }
    }

    override fun render() {
        renderTitle(content)
        renderDescription(content)
        renderStatus(content)
    }

    private fun renderTitle(project: Project) {
        tvTitle?.text = project.name
    }

    private fun renderDescription(project: Project) {
        tvDescription?.text = project.description
    }

    private fun renderStatus(project: Project) {
        tvStatus?.text = project.status.capitalize()
    }

    interface OnRowClicked {
        fun onProjectClicked(project: Project)
    }
}
