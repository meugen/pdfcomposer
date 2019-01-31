package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.presenter

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import ua.meugen.android.pdfcomposer.model.PdfExporter
import ua.meugen.android.pdfcomposer.model.actions.AppActionApi
import ua.meugen.android.pdfcomposer.model.actions.PdfExportRequest
import ua.meugen.android.pdfcomposer.model.data.PageContent
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.BasePresenter
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state.ProgressState
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.view.ProgressView
import ua.meugen.android.pdfcomposer.ui.rxloader.LifecycleHandler


class ProgressPresenterImpl(
        private val action: AppActionApi<PdfExportRequest, PdfExportProgressEvent>,
        private val lifecycleHandler: LifecycleHandler,
        private val view: ProgressView,
        private val pdfExporter: PdfExporter) : BasePresenter<ProgressState>(), ProgressPresenter {

    private var name: String? = null
    private var pages: List<PageContent>? = null

    override fun onSaveState(state: ProgressState) {
        super.onSaveState(state)
        state.pages = pages
        state.name = name
    }

    override fun onRestoreState(state: ProgressState) {
        super.onRestoreState(state)
        this.pages = state.pages
        this.name = state.name
    }

    override fun start() {
        val disposable = action
                .action(PdfExportRequest(name!!, pages!!))
                .compose(lifecycleHandler.load(LOADER_ID))
                .subscribe(
                        Consumer<PdfExportProgressEvent> { view.onUpdateProgress(it) },
                        Consumer<Throwable> { pdfExporter.onPdfExportError(it) },
                        Action { pdfExporter.onPdfExportSuccess() })
        getCompositeDisposable().add(disposable)
    }

    companion object {

        private const val LOADER_ID = 0
    }
}
