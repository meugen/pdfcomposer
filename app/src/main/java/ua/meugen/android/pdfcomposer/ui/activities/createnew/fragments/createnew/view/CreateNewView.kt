package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.view

import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.view.MvpView


interface CreateNewView : MvpView {

    fun loadImage()

    fun addPage()

    fun exportToPdf()

    fun textChanged(text: CharSequence)

    fun displayContent(imageUri: String?, text: String?)

    fun pageDidntComplete()
}
