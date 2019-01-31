package ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.presenter

import ua.meugen.android.pdfcomposer.model.data.PageContent
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.MvpPresenter
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.state.AskForNameState


interface AskForNamePresenter : MvpPresenter<AskForNameState> {

    val name: String?

    val pages: List<PageContent>?

    fun onNameChanged(name: String)
}
