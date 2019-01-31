package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state

import ua.meugen.android.pdfcomposer.model.data.PageContent
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState


interface ProgressState : MvpState {

    var name: String?

    var pages: List<PageContent>?

    companion object {

        const val PARAM_NAME = "name"
        const val PARAM_PAGES = "pages"
    }
}
