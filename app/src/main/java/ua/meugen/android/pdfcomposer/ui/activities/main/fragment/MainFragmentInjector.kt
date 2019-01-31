package ua.meugen.android.pdfcomposer.ui.activities.main.fragment

import ua.meugen.android.pdfcomposer.app.di.AppComponent
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.EmptyPresenterImpl
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.EmptyStateImpl
import ua.meugen.android.pdfcomposer.ui.activities.main.fragment.binding.MainBindingImpl

class MainFragmentInjector(private val fragment: MainFragment) : Injector {

    override fun inject(component: AppComponent) {
        fragment.state = EmptyStateImpl()
        fragment.binding = MainBindingImpl()
        fragment.presenter = EmptyPresenterImpl()
    }
}
