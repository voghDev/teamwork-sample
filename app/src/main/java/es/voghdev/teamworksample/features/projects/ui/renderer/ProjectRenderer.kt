package es.voghdev.teamworksample.features.projects.ui.renderer

import android.support.v4.content.ContextCompat
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

    companion object {
        var rowIndex = 0
    }

    override fun inflate(inflater: LayoutInflater?, parent: ViewGroup?): View =
            inflater?.inflate(R.layout.row_project, parent, false) ?: View(context)

    override fun setUpView(rootView: View?) {
        tvTitle = rootView?.findViewById(R.id.tvTitle)
        tvDescription = rootView?.findViewById(R.id.tvDescription)

        rowIndex = if (rowIndex + 1 == 4) 0 else rowIndex + 1
    }

    override fun hookListeners(rootView: View?) {
        rootView?.setOnClickListener {
            listener?.onProjectClicked(content)
        }
    }

    override fun render() {
        renderTitle(content)
        renderDescription(content)
        renderBackground(rowIndex)
    }

    private fun renderTitle(project: Project) {
        tvTitle?.text = project.name
    }

    private fun renderDescription(project: Project) {
        tvDescription?.text = project.description
    }

    private fun renderBackground(rowIndex: Int) {
        val colorResId = when (rowIndex) {
            0 -> R.color.teamwork_blue_darker
            1 -> R.color.teamwork_green
            2 -> R.color.teamwork_pink
            else -> R.color.teamwork_blue
        }

        rootView.setBackgroundColor(ContextCompat.getColor(context, colorResId))
    }

    interface OnRowClicked {
        fun onProjectClicked(project: Project)
    }
}
