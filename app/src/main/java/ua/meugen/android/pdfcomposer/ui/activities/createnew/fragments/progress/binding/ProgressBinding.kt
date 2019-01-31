package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.binding

import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding.Binding

/**
 * @author meugen
 */
interface ProgressBinding : Binding {

    fun displayProgress(event: PdfExportProgressEvent)
}
