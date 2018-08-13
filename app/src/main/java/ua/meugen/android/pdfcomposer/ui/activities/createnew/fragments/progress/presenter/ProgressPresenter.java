package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.presenter;

import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.MvpPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state.ProgressState;


public interface ProgressPresenter extends MvpPresenter<ProgressState> {

    void start();
}
