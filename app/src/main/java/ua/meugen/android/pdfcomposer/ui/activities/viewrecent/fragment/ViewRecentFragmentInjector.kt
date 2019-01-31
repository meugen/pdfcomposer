package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment

import ua.meugen.android.pdfcomposer.app.di.AppComponent
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.EmptyStateImpl
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.binding.ViewRecentBindingImpl
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.presenter.ViewRecentPresenterImpl

class ViewRecentFragmentInjector(private val fragment: ViewRecentFragment) : Injector {

    override fun inject(component: AppComponent) {
        fragment.binding = ViewRecentBindingImpl(
                fragment.context!!, fragment)
        fragment.presenter = ViewRecentPresenterImpl(
                component.pdfItemDao,
                Injector.provideLifecycleHandler(fragment),
                fragment)
        fragment.state = EmptyStateImpl()
    }
}
