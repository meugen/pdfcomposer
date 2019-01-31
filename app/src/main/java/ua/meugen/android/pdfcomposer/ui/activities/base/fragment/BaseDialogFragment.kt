package ua.meugen.android.pdfcomposer.ui.activities.base.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment

import ua.meugen.android.pdfcomposer.app.PdfComposer
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.MvpPresenter
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.BaseState
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.view.MvpView


abstract class BaseDialogFragment<S : MvpState, P : MvpPresenter<S>> : DialogFragment(), MvpView {

    lateinit var presenter: P
    lateinit var state: S

    override fun onAttach(context: Context?) {
        inject(context)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            (state as BaseState).attachBundle(arguments)
        } else {
            (state as BaseState).attachBundle(savedInstanceState)
        }
        presenter.onRestoreState(state)
        (state as BaseState).detachBundle()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        (state as BaseState).attachBundle(outState)
        presenter.onSaveState(state)
        (state as BaseState).detachBundle()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clear()
    }

    private fun inject(context: Context?) {
        val injector = createInjector()
        injector.inject(PdfComposer.from(context!!).appComponent)
    }

    protected abstract fun createInjector(): Injector
}
