package ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter

import io.reactivex.disposables.CompositeDisposable
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState

open class BasePresenter<S : MvpState> : MvpPresenter<S> {

    private var compositeDisposable: CompositeDisposable? = null

    override fun onSaveState(state: S) {}

    override fun onRestoreState(state: S) {}

    override fun clear() {
        compositeDisposable?.clear()
        compositeDisposable = null
    }

    protected fun getCompositeDisposable(): CompositeDisposable {
        compositeDisposable?.let { return it }
        return CompositeDisposable().also { compositeDisposable = it }
    }
}
