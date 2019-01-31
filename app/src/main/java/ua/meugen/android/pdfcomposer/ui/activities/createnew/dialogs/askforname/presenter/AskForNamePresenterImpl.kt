package ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.presenter

import ua.meugen.android.pdfcomposer.model.data.PageContent
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.BasePresenter
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.state.AskForNameState


class AskForNamePresenterImpl : BasePresenter<AskForNameState>(), AskForNamePresenter {

    override var name: String? = null
        private set
    override var pages: List<PageContent>? = null


    override fun onSaveState(state: AskForNameState) {
        super.onSaveState(state)
        state.pages = pages
        state.name = name
    }

    override fun onRestoreState(state: AskForNameState) {
        super.onRestoreState(state)
        this.name = state.name
        this.pages = state.pages
    }

    override fun onNameChanged(name: String) {
        this.name = name
    }
}
