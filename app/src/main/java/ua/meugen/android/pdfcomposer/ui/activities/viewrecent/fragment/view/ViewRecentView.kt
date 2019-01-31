package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.view

import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.view.MvpView


interface ViewRecentView : MvpView {

    fun displayItems(items: List<PdfItemEntity>)
}
