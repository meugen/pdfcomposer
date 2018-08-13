package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ua.meugen.android.pdfcomposer.app.di.qualifiers.ActivityContext;
import ua.meugen.android.pdfcomposer.databinding.ItemExportedPdfBinding;
import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity;
import ua.meugen.android.pdfcomposer.ui.activities.base.BaseActivityModule;

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

    @Override
    public Holder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final ItemExportedPdfBinding binding = ItemExportedPdfBinding
                .inflate(inflater, parent, false);
        final Holder holder = new Holder(binding);
        binding.container.setOnClickListener(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.binding.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ItemExportedPdfBinding binding;

        public Holder(final ItemExportedPdfBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onClick(final View v) {
            listener.onPdfItemClick(binding.getItem());
        }
    }
}
