package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state

import ua.meugen.android.pdfcomposer.model.data.PageContent
import ua.meugen.android.pdfcomposer.model.utils.toArrayList
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.BaseState


class ProgressStateImpl : BaseState(), ProgressState {

    override var name: String?
        get() = bundle!!.getString(ProgressState.PARAM_NAME)
        set(name) = bundle!!.putString(ProgressState.PARAM_NAME, name)

    override var pages: List<PageContent>?
        get() = bundle!!.getParcelableArrayList(ProgressState.PARAM_PAGES)
        set(pages) = bundle!!.putParcelableArrayList(ProgressState.PARAM_PAGES, pages!!.toArrayList)
}
