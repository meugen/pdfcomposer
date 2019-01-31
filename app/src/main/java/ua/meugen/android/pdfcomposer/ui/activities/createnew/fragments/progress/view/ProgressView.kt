package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.view

import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.view.MvpView


interface ProgressView : MvpView {

    fun onUpdateProgress(event: PdfExportProgressEvent)
}
