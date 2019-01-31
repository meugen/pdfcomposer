package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state

import ua.meugen.android.pdfcomposer.model.data.PageContent
import ua.meugen.android.pdfcomposer.model.utils.toArrayList
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.BaseState


class CreateNewStateImpl : BaseState(), CreateNewState {

    override var pages: List<PageContent>?
        get() = bundle!!.getParcelableArrayList(PARAM_PAGES)
        set(pages) = bundle!!.putParcelableArrayList(PARAM_PAGES, pages!!.toArrayList)

    companion object {

        private const val PARAM_PAGES = "pages"
    }
}
