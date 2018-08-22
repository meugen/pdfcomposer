package ua.meugen.android.pdfcomposer.ui.activities.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import ua.meugen.android.pdfcomposer.app.PdfComposer;
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.MvpPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.BaseState;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.view.MvpView;


public abstract class BaseDialogFragment<S extends MvpState, P extends MvpPresenter<S>>
        extends DialogFragment implements MvpView {

    public P presenter;
    public S state;

    @Override
    public void onAttach(final Context context) {
        inject(context);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            ((BaseState) state).attachBundle(getArguments());
        } else {
            ((BaseState) state).attachBundle(savedInstanceState);
        }
        presenter.onRestoreState(state);
        ((BaseState) state).detachBundle();
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        ((BaseState) state).attachBundle(outState);
        presenter.onSaveState(state);
        ((BaseState) state).detachBundle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.clear();
    }

    private void inject(final Context context) {
        final Injector injector = createInjector();
        injector.inject(PdfComposer.from(context).getAppComponent());
    }

    protected abstract Injector createInjector();
}
