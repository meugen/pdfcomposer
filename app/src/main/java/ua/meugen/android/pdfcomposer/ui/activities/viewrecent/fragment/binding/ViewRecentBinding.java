package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.binding;

import java.util.List;

import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding.Binding;

/**
 * @author meugen
 */
public interface ViewRecentBinding extends Binding {

    void setupRecycler();

    void displayNoItems();

    void displayItems(List<PdfItemEntity> items);
}
