package ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.state

import ua.meugen.android.pdfcomposer.model.data.PageContent
import ua.meugen.android.pdfcomposer.model.utils.toArrayList
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.BaseState


class AskForNameStateImpl : BaseState(), AskForNameState {

    override var name: String?
        get() = bundle!!.getString(AskForNameState.PARAM_NAME)
        set(name) = bundle!!.putString(AskForNameState.PARAM_NAME, name)

    override var pages: List<PageContent>?
        get() = bundle!!.getParcelableArrayList(AskForNameState.PARAM_PAGES)
        set(pages) = bundle!!.putParcelableArrayList(AskForNameState.PARAM_PAGES, pages!!.toArrayList)
}
