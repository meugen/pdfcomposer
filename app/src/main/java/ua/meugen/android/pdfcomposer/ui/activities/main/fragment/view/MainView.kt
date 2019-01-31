package ua.meugen.android.pdfcomposer.ui.activities.main.fragment.view

import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.view.MvpView

interface MainView : MvpView {

    fun createNewDocument()

    fun viewRecentDocuments()
}
