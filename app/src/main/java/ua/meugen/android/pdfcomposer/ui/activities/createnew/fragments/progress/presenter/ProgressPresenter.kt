package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.presenter

import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.MvpPresenter
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state.ProgressState


interface ProgressPresenter : MvpPresenter<ProgressState> {

    fun start()
}
