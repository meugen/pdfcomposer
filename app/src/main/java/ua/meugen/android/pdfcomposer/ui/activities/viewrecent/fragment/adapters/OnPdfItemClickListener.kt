package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.adapters

import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity

interface OnPdfItemClickListener {

    fun onPdfItemClick(entity: PdfItemEntity)
}
