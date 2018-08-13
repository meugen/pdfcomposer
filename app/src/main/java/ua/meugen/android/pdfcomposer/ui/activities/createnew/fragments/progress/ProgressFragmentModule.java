package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import ua.meugen.android.pdfcomposer.app.di.PerFragment;
import ua.meugen.android.pdfcomposer.model.actions.AppActionApi;
import ua.meugen.android.pdfcomposer.model.actions.PdfExportActionApi;
import ua.meugen.android.pdfcomposer.model.actions.PdfExportRequest;
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragmentModule;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.presenter.ProgressPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.presenter.ProgressPresenterImpl;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state.ProgressState;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state.ProgressStateImpl;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.view.ProgressView;

@Module(includes = BaseFragmentModule.class)
public abstract class ProgressFragmentModule {

    @Binds @PerFragment
    abstract Fragment bindFragment(final ProgressFragment fragment);

    @Binds @PerFragment
    abstract ProgressPresenter bindPresenter(final ProgressPresenterImpl impl);

    @Binds @PerFragment
    abstract ProgressState bindState(final ProgressStateImpl impl);

    @Binds @PerFragment
    abstract ProgressView bindView(final ProgressFragment fragment);

    @Binds @PerFragment
    abstract AppActionApi<PdfExportRequest, PdfExportProgressEvent> bindPdfExportActionApi(
            final PdfExportActionApi api);
}
