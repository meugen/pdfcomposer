package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.binding

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import ua.meugen.android.pdfcomposer.R
import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding.BaseBinding
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.adapters.OnPdfItemClickListener
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.adapters.PdfItemsAdapter

/**
 * @author meugen
 */
class ViewRecentBindingImpl(
        private val context: Context,
        private val listener: OnPdfItemClickListener) : BaseBinding(), ViewRecentBinding {

    private var adapter: PdfItemsAdapter? = null

    override fun setupRecycler() {
        val recycler = get<RecyclerView>(R.id.recycler)
        recycler.addItemDecoration(DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL))
        recycler.layoutManager = GridLayoutManager(context,
                context.resources.getInteger(R.integer.recent_pdf_items_span_count))
        adapter = PdfItemsAdapter(context, listener)
        recycler.adapter = adapter
    }

    override fun displayNoItems() {
        get<View>(R.id.no_items_label).visibility = View.VISIBLE
        get<View>(R.id.recycler).visibility = View.GONE
    }

    override fun displayItems(items: List<PdfItemEntity>) {
        adapter!!.swapItems(items)
        if (items.size == 0) {
            displayNoItems()
            return
        }
        get<View>(R.id.no_items_label).visibility = View.GONE
        get<View>(R.id.recycler).visibility = View.VISIBLE
    }
}
