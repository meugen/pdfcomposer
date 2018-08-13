package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.view;

import java.util.List;

import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.view.MvpView;


public interface ViewRecentView extends MvpView {

    void displayItems(List<PdfItemEntity> items);
}
