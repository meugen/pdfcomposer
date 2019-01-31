package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.presenter

import java.util.ArrayList

import ua.meugen.android.pdfcomposer.model.PdfExporter
import ua.meugen.android.pdfcomposer.model.data.PageContent
import ua.meugen.android.pdfcomposer.model.utils.toArrayList
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.BasePresenter
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state.CreateNewState
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.view.CreateNewView


class CreateNewPresenterImpl(
        private val view: CreateNewView,
        private val pdfExporter: PdfExporter) : BasePresenter<CreateNewState>(), CreateNewPresenter {

    private var pages: MutableList<PageContent>? = null

    private val lastPage: PageContent
        get() = pages!![pages!!.size - 1]

    override fun onSaveState(state: CreateNewState) {
        super.onSaveState(state)
        state.pages = pages
    }

    override fun onRestoreState(state: CreateNewState) {
        super.onRestoreState(state)
        this.pages = state.pages?.toArrayList
    }

    override fun setup() {
        if (pages == null) {
            pages = ArrayList()
            pages!!.add(PageContent())
        }
        val lastPage = lastPage
        view.displayContent(lastPage.imageUri, lastPage.text)
    }

    override fun onImageSelected(imageUri: String) {
        val lastPage = lastPage
        lastPage.imageUri = imageUri
        view.displayContent(imageUri, lastPage.text!!)
    }

    override fun onTextChanged(text: String) {
        val lastPage = lastPage
        lastPage.text = text
    }

    private fun checkLastPageValid(): Boolean {
        val lastPage = lastPage
        return (lastPage.imageUri != null
                && lastPage.text != null
                && !lastPage.text!!.isEmpty())
    }

    override fun addPage() {
        if (!checkLastPageValid()) {
            view.pageDidntComplete()
            return
        }
        pages!!.add(PageContent())
        view.displayContent(null, null)
    }

    override fun exportPdf() {
        if (!checkLastPageValid()) {
            view.pageDidntComplete()
            return
        }
        pdfExporter.exportPages(pages!!)
    }
}
