package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.view;

import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.view.MvpView;


public interface CreateNewView extends MvpView {

    void loadImage();

    void addPage();

    void exportToPdf();

    void textChanged(CharSequence text);

    void displayContent(String imageUri, String text);

    void pageDidntComplete();
}
