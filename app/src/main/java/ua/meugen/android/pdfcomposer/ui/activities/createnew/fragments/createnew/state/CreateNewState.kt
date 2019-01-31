package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state

import ua.meugen.android.pdfcomposer.model.data.PageContent
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState


interface CreateNewState : MvpState {

    var pages: List<PageContent>?
}
