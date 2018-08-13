package ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter;

import io.reactivex.disposables.CompositeDisposable;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;

public class BasePresenter<S extends MvpState> implements MvpPresenter<S> {

    private CompositeDisposable compositeDisposable;

    @Override
    public void onSaveState(final S state) {}

    @Override
    public void onRestoreState(final S state) {}

    @Override
    public void clear() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        compositeDisposable = null;
    }

    protected final CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }
}
