package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.presenter

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import ua.meugen.android.pdfcomposer.model.db.dao.PdfItemDao
import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.BasePresenter
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.view.ViewRecentView
import ua.meugen.android.pdfcomposer.ui.rxloader.LifecycleHandler


class ViewRecentPresenterImpl(
        private val pdfItemDao: PdfItemDao,
        private val lifecycleHandler: LifecycleHandler,
        private val view: ViewRecentView) : BasePresenter<MvpState>(), ViewRecentPresenter {

    override fun load() {
        val disposable = pdfItemDao.allPdfItems
                .toObservable()
                .compose<List<PdfItemEntity>>(lifecycleHandler.load<List<PdfItemEntity>>(0))
                .subscribe(Consumer<List<PdfItemEntity>> { view.displayItems(it) })
        getCompositeDisposable().add(disposable)
    }
}
