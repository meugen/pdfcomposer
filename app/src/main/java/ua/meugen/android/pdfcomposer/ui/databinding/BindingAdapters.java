package ua.meugen.android.pdfcomposer.ui.databinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent;

public class BindingAdapters {

    @BindingAdapter("onClick")
    public static void setOnClickListener(
            final View view,
            final OnClickListener listener) {
        view.setOnClickListener(v -> listener.onClick());
    }

    @BindingAdapter("progress")
    public static void setProgress(
            final ProgressBar progressBar,
            final PdfExportProgressEvent event) {
        if (event == null) {
            return;
        }
        progressBar.setProgress(event.completedPages);
        progressBar.setMax(event.totalPages);
    }

    @BindingAdapter("progress")
    public static void setProgress(
            final TextView view,
            final PdfExportProgressEvent event) {
        if (event == null) {
            return;
        }
        final Context context = view.getContext();
        view.setText(context.getString(R.string.pdf_export_progress,
                event.completedPages, event.totalPages));
    }

    @BindingAdapter("size")
    public static void setSize(final TextView view, final long size) {
        String text;

        final int unit = 1024;
        if (size < unit) {
            text = size + " B";
        } else {
            int exp = (int) (Math.log(size) / Math.log(unit));
            String pre = "" + "KMGTPE".charAt(exp-1);
            text = String.format(Locale.ENGLISH,
                    "%.1f %sB",
                    size / Math.pow(unit, exp), pre);
        }
        view.setText(text);
    }

    @BindingAdapter("date")
    public static void setDate(final TextView view, final Date date) {
        view.setText(DateFormat.getDateInstance().format(date));
    }

    public interface OnClickListener {

        void onClick();
    }
}
