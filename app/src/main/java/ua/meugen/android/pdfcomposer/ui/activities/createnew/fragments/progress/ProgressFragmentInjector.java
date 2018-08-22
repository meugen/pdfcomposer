package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress;

import ua.meugen.android.pdfcomposer.app.di.AppComponent;
import ua.meugen.android.pdfcomposer.model.PdfExporter;
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.binding.ProgressBindingImpl;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.presenter.ProgressPresenterImpl;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state.ProgressStateImpl;

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
        fragment.state = new ProgressStateImpl();
        fragment.presenter = new ProgressPresenterImpl(
                component.getPdfExportActionApi(),
                Injector.provideLifecycleHandler(fragment),
                fragment,
                (PdfExporter) fragment.getContext());
    }
}
