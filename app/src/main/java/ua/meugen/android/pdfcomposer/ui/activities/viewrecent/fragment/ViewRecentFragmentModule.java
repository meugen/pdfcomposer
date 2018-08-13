package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragmentModule;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.EmptyStateImpl;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.adapters.OnPdfItemClickListener;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.presenter.ViewRecentPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.presenter.ViewRecentPresenterImpl;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.view.ViewRecentView;

@Module(includes = BaseFragmentModule.class)
public abstract class ViewRecentFragmentModule {

    @Binds
    abstract Fragment bindFragment(final ViewRecentFragment fragment);

    @Binds
    abstract ViewRecentPresenter bindPresenter(final ViewRecentPresenterImpl impl);

    @Binds
    abstract MvpState bindState(final EmptyStateImpl impl);

    @Binds
    abstract ViewRecentView bindView(final ViewRecentFragment fragment);

    @Binds
    abstract OnPdfItemClickListener bindPdfItemClickListener(final ViewRecentFragment fragment);
}
