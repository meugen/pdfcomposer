package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import ua.meugen.android.pdfcomposer.model.PdfExporter;
import ua.meugen.android.pdfcomposer.model.actions.AppActionApi;
import ua.meugen.android.pdfcomposer.model.actions.PdfExportRequest;
import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.BasePresenter;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state.ProgressState;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.view.ProgressView;
import ua.meugen.android.pdfcomposer.ui.rxloader.LifecycleHandler;


public class ProgressPresenterImpl extends BasePresenter<ProgressState>
        implements ProgressPresenter {

    private static final int LOADER_ID = 0;

    private final AppActionApi<PdfExportRequest, PdfExportProgressEvent> action;
    private final LifecycleHandler lifecycleHandler;
    private final ProgressView view;
    private final PdfExporter pdfExporter;

    private String name;
    private List<PageContent> pages;

    public ProgressPresenterImpl(
            final AppActionApi<PdfExportRequest, PdfExportProgressEvent> action,
            final LifecycleHandler lifecycleHandler,
            final ProgressView view,
            final PdfExporter pdfExporter) {
        this.action = action;
        this.lifecycleHandler = lifecycleHandler;
        this.view = view;
        this.pdfExporter = pdfExporter;
    }

    @Override
    public void onSaveState(final ProgressState state) {
        super.onSaveState(state);
        state.setPages(pages);
        state.setName(name);
    }

    @Override
    public void onRestoreState(final ProgressState state) {
        super.onRestoreState(state);
        this.pages = state.getPages();
        this.name = state.getName();
    }

    @Override
    public void start() {
        final Disposable disposable = action
                .action(new PdfExportRequest(name, pages))
                .compose(lifecycleHandler.load(LOADER_ID))
                .subscribe(
                        view::onUpdateProgress,
                        pdfExporter::onPdfExportError,
                        pdfExporter::onPdfExportSuccess);
        getCompositeDisposable().add(disposable);
    }
}
