package ua.meugen.android.pdfcomposer.model.actions

import ua.meugen.android.pdfcomposer.model.data.PageContent

/**
 * Created by meugen on 22.12.2017.
 */

class PdfExportRequest(
        val name: String,
        val pages: List<PageContent>) {

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val that = o as PdfExportRequest?

        return if (name != that!!.name) false else pages == that.pages
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + pages.hashCode()
        return result
    }
}
