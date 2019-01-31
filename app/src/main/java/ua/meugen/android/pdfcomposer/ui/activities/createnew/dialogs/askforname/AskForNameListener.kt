package ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname

import ua.meugen.android.pdfcomposer.model.data.PageContent

interface AskForNameListener {

    fun onDocumentNamed(name: String, pages: List<PageContent>)
}
