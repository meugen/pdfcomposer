package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.presenter

import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.MvpPresenter
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState


interface ViewRecentPresenter : MvpPresenter<MvpState> {

    fun load()
}
