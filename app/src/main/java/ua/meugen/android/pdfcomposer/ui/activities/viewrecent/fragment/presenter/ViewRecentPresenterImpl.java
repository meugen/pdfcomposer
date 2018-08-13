package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.presenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import ua.meugen.android.pdfcomposer.model.db.dao.PdfItemDao;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.BasePresenter;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.view.ViewRecentView;
import ua.meugen.android.pdfcomposer.ui.rxloader.LifecycleHandler;


public class ViewRecentPresenterImpl extends BasePresenter<MvpState>
        implements ViewRecentPresenter {

    @Inject PdfItemDao pdfItemDao;
    @Inject LifecycleHandler lifecycleHandler;
    @Inject ViewRecentView view;

    @Inject
    ViewRecentPresenterImpl() {}

    @Override
    public void load() {
        final Disposable disposable = pdfItemDao.getAllPdfItems()
                .toObservable()
                .compose(lifecycleHandler.load(0))
                .subscribe(view::displayItems);
        getCompositeDisposable().add(disposable);
    }
}
