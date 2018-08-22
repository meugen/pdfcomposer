package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew;

import ua.meugen.android.pdfcomposer.app.di.AppComponent;
import ua.meugen.android.pdfcomposer.model.PdfExporter;
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.binding.CreateNewBindingImpl;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.presenter.CreateNewPresenterImpl;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state.CreateNewStateImpl;

/**
 * @author meugen
 */
class CreateNewFragmentInjector implements Injector {

    private final CreateNewFragment fragment;

    CreateNewFragmentInjector(final CreateNewFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void inject(final AppComponent component) {
        fragment.state = new CreateNewStateImpl();
        fragment.presenter = new CreateNewPresenterImpl(
                fragment, (PdfExporter) fragment.getActivity());
        fragment.binding = new CreateNewBindingImpl();
    }
}
