package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment;

import ua.meugen.android.pdfcomposer.app.di.AppComponent;
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.EmptyStateImpl;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.binding.ViewRecentBindingImpl;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.presenter.ViewRecentPresenterImpl;

public class ViewRecentFragmentInjector implements Injector {

    private final ViewRecentFragment fragment;

    public ViewRecentFragmentInjector(final ViewRecentFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void inject(final AppComponent component) {
        fragment.binding = new ViewRecentBindingImpl(
                fragment.getContext(), fragment);
        fragment.presenter = new ViewRecentPresenterImpl(
                component.getPdfItemDao(),
                Injector.provideLifecycleHandler(fragment),
                fragment);
        fragment.state = new EmptyStateImpl();
    }
}
