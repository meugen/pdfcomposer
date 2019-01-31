package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.binding

import android.content.Context
import android.widget.ProgressBar
import android.widget.TextView

import ua.meugen.android.pdfcomposer.R
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding.BaseBinding

/**
 * @author meugen
 */
class ProgressBindingImpl(private val context: Context) : BaseBinding(), ProgressBinding {

    override fun displayProgress(event: PdfExportProgressEvent) {
        val progressBar = get<ProgressBar>(R.id.progress_bar)
        progressBar.progress = event.completedPages
        progressBar.max = event.totalPages

        val textView = get<TextView>(R.id.progress_text)
        textView.text = context.getString(R.string.pdf_export_progress,
                event.completedPages, event.totalPages)
    }
}
