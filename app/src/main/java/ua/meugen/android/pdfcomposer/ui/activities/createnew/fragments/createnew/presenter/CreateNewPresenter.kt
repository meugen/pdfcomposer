package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.presenter

import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.MvpPresenter
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state.CreateNewState


interface CreateNewPresenter : MvpPresenter<CreateNewState> {

    fun setup()

    fun onImageSelected(imageUri: String)

    fun onTextChanged(text: String)

    fun addPage()

    fun exportPdf()
}
