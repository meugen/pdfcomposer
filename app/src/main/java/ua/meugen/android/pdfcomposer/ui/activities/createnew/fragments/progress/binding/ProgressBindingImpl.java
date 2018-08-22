package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.binding;

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding.BaseBinding;

/**
 * @author meugen
 */
public class ProgressBindingImpl extends BaseBinding
        implements ProgressBinding {

    private final Context context;

    public ProgressBindingImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void displayProgress(final PdfExportProgressEvent event) {
        final ProgressBar progressBar = get(R.id.progress_bar);
        progressBar.setProgress(event.completedPages);
        progressBar.setMax(event.totalPages);

        final TextView textView = get(R.id.progress_text);
        textView.setText(context.getString(R.string.pdf_export_progress,
                event.completedPages, event.totalPages));
    }
}
