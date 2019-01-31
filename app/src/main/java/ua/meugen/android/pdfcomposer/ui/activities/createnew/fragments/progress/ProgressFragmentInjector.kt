package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress

import ua.meugen.android.pdfcomposer.app.di.AppComponent
import ua.meugen.android.pdfcomposer.model.PdfExporter
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.binding.ProgressBindingImpl
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.presenter.ProgressPresenterImpl
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state.ProgressStateImpl

/**
 * @author meugen
 */
class ProgressFragmentInjector(private val fragment: ProgressFragment) : Injector {

    override fun inject(component: AppComponent) {
        fragment.binding = ProgressBindingImpl(fragment.context!!)
        fragment.state = ProgressStateImpl()
        fragment.presenter = ProgressPresenterImpl(
                component.pdfExportActionApi,
                Injector.provideLifecycleHandler(fragment),
                fragment,
                (fragment.context as PdfExporter?)!!)
    }
}
