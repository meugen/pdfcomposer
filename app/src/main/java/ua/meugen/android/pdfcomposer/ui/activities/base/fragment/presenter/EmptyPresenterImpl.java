package ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter;

import javax.inject.Inject;

import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;

public class EmptyPresenterImpl extends BasePresenter<MvpState> implements MvpPresenter<MvpState> {

    @Inject
    EmptyPresenterImpl() {}
}
