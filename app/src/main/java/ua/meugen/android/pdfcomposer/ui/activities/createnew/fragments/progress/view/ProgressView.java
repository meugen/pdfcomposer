package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.view;

import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.view.MvpView;


public interface ProgressView extends MvpView {

    void onUpdateProgress(PdfExportProgressEvent event);
}
