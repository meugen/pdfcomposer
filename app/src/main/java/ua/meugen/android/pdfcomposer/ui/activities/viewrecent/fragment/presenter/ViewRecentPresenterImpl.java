package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.presenter;

import io.reactivex.disposables.Disposable;
import ua.meugen.android.pdfcomposer.model.db.dao.PdfItemDao;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.BasePresenter;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.view.ViewRecentView;
import ua.meugen.android.pdfcomposer.ui.rxloader.LifecycleHandler;


public class ViewRecentPresenterImpl extends BasePresenter<MvpState>
        implements ViewRecentPresenter {

    private final PdfItemDao pdfItemDao;
    private final LifecycleHandler lifecycleHandler;
    private final ViewRecentView view;

    public ViewRecentPresenterImpl(
            final PdfItemDao pdfItemDao,
            final LifecycleHandler lifecycleHandler,
            final ViewRecentView view) {
        this.pdfItemDao = pdfItemDao;
        this.lifecycleHandler = lifecycleHandler;
        this.view = view;
    }

    @Override
    public void load() {
        final Disposable disposable = pdfItemDao.getAllPdfItems()
                .toObservable()
                .compose(lifecycleHandler.load(0))
                .subscribe(view::displayItems);
        getCompositeDisposable().add(disposable);
    }
}
