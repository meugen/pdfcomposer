package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress;

import ua.meugen.android.pdfcomposer.app.di.AppComponent;
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.binding.ProgressBindingImpl;

/**
 * @author meugen
 */
public class ProgressFragmentInjector implements Injector {

    private final ProgressFragment fragment;

    public ProgressFragmentInjector(final ProgressFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void inject(final AppComponent component) {
        fragment.binding = new ProgressBindingImpl(fragment.getContext());
    }
}
