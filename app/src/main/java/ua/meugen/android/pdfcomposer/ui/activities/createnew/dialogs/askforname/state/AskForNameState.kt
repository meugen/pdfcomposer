package ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.state

import ua.meugen.android.pdfcomposer.model.data.PageContent
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState


interface AskForNameState : MvpState {

    var name: String?

    var pages: List<PageContent>?

    companion object {

        val PARAM_NAME = "name"
        val PARAM_PAGES = "pages"
    }
}
