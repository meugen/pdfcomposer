package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew

import ua.meugen.android.pdfcomposer.app.di.AppComponent
import ua.meugen.android.pdfcomposer.model.PdfExporter
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.binding.CreateNewBindingImpl
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.presenter.CreateNewPresenterImpl
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state.CreateNewStateImpl

/**
 * @author meugen
 */
internal class CreateNewFragmentInjector(private val fragment: CreateNewFragment) : Injector {

    override fun inject(component: AppComponent) {
        fragment.state = CreateNewStateImpl()
        fragment.presenter = CreateNewPresenterImpl(
                fragment, (fragment.activity as PdfExporter?)!!)
        fragment.binding = CreateNewBindingImpl()
    }
}
