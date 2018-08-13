package ua.meugen.android.pdfcomposer.ui.activities.main.fragment.view;

import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.view.MvpView;

public interface MainView extends MvpView {

    void createNewDocument();

    void viewRecentDocuments();
}
