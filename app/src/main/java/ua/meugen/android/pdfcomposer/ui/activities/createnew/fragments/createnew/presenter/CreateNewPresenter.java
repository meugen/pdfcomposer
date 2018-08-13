package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.presenter;

import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.MvpPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state.CreateNewState;


public interface CreateNewPresenter extends MvpPresenter<CreateNewState> {

    void setup();

    void onImageSelected(String imageUri);

    void onTextChanged(String text);

    void addPage();

    void exportPdf();
}
