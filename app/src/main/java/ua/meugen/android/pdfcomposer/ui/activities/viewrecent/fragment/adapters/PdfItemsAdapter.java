package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.app.di.qualifiers.ActivityContext;
import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity;

public class PdfItemsAdapter extends RecyclerView.Adapter<PdfItemsAdapter.Holder> {

    private final LayoutInflater inflater;
    private OnPdfItemClickListener listener;
    private List<PdfItemEntity> items;

    @Inject
    public PdfItemsAdapter(
            @ActivityContext final Context context,
            final OnPdfItemClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
        items = Collections.emptyList();
    }

    public void swapItems(final List<PdfItemEntity> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(
            @NonNull final ViewGroup parent,
            final int viewType) {
        final View view = inflater.inflate(R.layout.item_exported_pdf,
                parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView nameView;
        private final TextView sizeView;
        private final TextView dateView;

        private PdfItemEntity entity;

        Holder(final View view) {
            super(view);
            this.nameView = view.findViewById(R.id.name);
            this.sizeView = view.findViewById(R.id.size);
            this.dateView = view.findViewById(R.id.date);

            view.findViewById(R.id.container).setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            listener.onPdfItemClick(entity);
        }

        public void bind(final PdfItemEntity entity) {
            this.entity = entity;

            this.nameView.setText(entity.name);
            this.sizeView.setText(sizeToText(entity.size));
            this.dateView.setText(dateToText(entity.date));
        }

        private String sizeToText(final long size) {
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
            return text;
        }

        private String dateToText(final Date date) {
            return DateFormat.getDateInstance().format(date);
        }
    }
}
