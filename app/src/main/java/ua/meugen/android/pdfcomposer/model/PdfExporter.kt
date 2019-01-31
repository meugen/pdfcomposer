package ua.meugen.android.pdfcomposer.model

import ua.meugen.android.pdfcomposer.model.data.PageContent

interface PdfExporter {

    fun exportPages(pages: List<PageContent>)

    fun onPdfExportSuccess()

    fun onPdfExportError(th: Throwable)
}
