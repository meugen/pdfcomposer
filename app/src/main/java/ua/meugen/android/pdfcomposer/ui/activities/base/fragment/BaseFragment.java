package ua.meugen.android.pdfcomposer.ui.activities.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import ua.meugen.android.pdfcomposer.app.PdfComposer;
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding.Binding;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.MvpPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.BaseState;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.view.MvpView;

public abstract class BaseFragment<S extends MvpState, P extends MvpPresenter<S>, B extends Binding> extends Fragment
        implements MvpView {

    public P presenter;
    public S state;
    public B binding;

    @Override
    public void onViewCreated(
            @NonNull final View view,
            @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.attachView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.detachView();
    }

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
