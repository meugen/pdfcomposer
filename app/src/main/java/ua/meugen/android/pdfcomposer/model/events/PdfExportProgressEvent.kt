package ua.meugen.android.pdfcomposer.model.events

class PdfExportProgressEvent(
        val completedPages: Int,
        val totalPages: Int) {

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val that = o as PdfExportProgressEvent?

        return if (completedPages != that!!.completedPages) false else totalPages == that.totalPages
    }

    override fun hashCode(): Int {
        var result = completedPages
        result = 31 * result + totalPages
        return result
    }
}
