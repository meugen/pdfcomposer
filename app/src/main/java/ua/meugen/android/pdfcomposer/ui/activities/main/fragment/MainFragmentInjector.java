package ua.meugen.android.pdfcomposer.ui.activities.main.fragment;

import ua.meugen.android.pdfcomposer.app.di.AppComponent;
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.EmptyPresenterImpl;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.EmptyStateImpl;
import ua.meugen.android.pdfcomposer.ui.activities.main.fragment.binding.MainBindingImpl;

public class MainFragmentInjector implements Injector {

    private final MainFragment fragment;

    public MainFragmentInjector(final MainFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void inject(final AppComponent component) {
        fragment.state = new EmptyStateImpl();
        fragment.binding = new MainBindingImpl();
        fragment.presenter = new EmptyPresenterImpl();
    }
}
