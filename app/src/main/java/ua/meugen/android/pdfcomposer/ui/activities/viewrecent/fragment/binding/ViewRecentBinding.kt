package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.binding

import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding.Binding

/**
 * @author meugen
 */
interface ViewRecentBinding : Binding {

    fun setupRecycler()

    fun displayNoItems()

    fun displayItems(items: List<PdfItemEntity>)
}
