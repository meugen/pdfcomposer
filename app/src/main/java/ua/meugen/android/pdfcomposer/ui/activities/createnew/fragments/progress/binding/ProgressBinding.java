package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.binding;

import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding.Binding;

/**
 * @author meugen
 */
public interface ProgressBinding extends Binding {

    void displayProgress(PdfExportProgressEvent event);
}
