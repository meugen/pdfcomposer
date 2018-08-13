package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.binding;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.app.di.qualifiers.ActivityContext;
import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding.BaseBinding;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.adapters.PdfItemsAdapter;

/**
 * @author meugen
 */
public class ViewRecentBindingImpl extends BaseBinding
        implements ViewRecentBinding {

    @Inject @ActivityContext Context context;
    @Inject PdfItemsAdapter adapter;

    @Inject
    ViewRecentBindingImpl() {}

    @Override
    public void setupRecycler() {
        final RecyclerView recycler = get(R.id.recycler);
        recycler.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL));
        recycler.setLayoutManager(new GridLayoutManager(context,
                context.getResources().getInteger(R.integer.recent_pdf_items_span_count)));
        recycler.setAdapter(adapter);
    }

    @Override
    public void displayNoItems() {
        get(R.id.no_items_label).setVisibility(View.VISIBLE);
        get(R.id.recycler).setVisibility(View.GONE);
    }

    @Override
    public void displayItems(final List<PdfItemEntity> items) {
        adapter.swapItems(items);
        if (items.size() == 0) {
            displayNoItems();
            return;
        }
        get(R.id.no_items_label).setVisibility(View.GONE);
        get(R.id.recycler).setVisibility(View.VISIBLE);
    }
}
