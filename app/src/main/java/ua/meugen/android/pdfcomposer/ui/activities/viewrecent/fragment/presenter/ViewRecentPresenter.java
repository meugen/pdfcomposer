package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.presenter;

import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.MvpPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;


public interface ViewRecentPresenter extends MvpPresenter<MvpState> {

    void load();
}
