package ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter;

import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;

/**
 * Created by meugen on 21.12.2017.
 */

public interface MvpPresenter<S extends MvpState> {

    void onSaveState(S state);

    void onRestoreState(S state);

    void clear();
}
