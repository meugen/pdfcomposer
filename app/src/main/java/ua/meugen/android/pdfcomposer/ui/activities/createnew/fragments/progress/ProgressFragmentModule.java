package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import ua.meugen.android.pdfcomposer.model.actions.AppActionApi;
import ua.meugen.android.pdfcomposer.model.actions.PdfExportActionApi;
import ua.meugen.android.pdfcomposer.model.actions.PdfExportRequest;
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragmentModule;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.binding.ProgressBinding;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.binding.ProgressBindingImpl;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.presenter.ProgressPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.presenter.ProgressPresenterImpl;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state.ProgressState;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state.ProgressStateImpl;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.view.ProgressView;

@Module(includes = BaseFragmentModule.class)
public abstract class ProgressFragmentModule {

    @Binds
    abstract Fragment bindFragment(final ProgressFragment fragment);

    @Binds
    abstract ProgressPresenter bindPresenter(final ProgressPresenterImpl impl);

    @Binds
    abstract ProgressState bindState(final ProgressStateImpl impl);

    @Binds
    abstract ProgressView bindView(final ProgressFragment fragment);

    @Binds
    abstract ProgressBinding bindBinding(final ProgressBindingImpl impl);

    @Binds
    abstract AppActionApi<PdfExportRequest, PdfExportProgressEvent> bindPdfExportActionApi(
            final PdfExportActionApi api);
}
