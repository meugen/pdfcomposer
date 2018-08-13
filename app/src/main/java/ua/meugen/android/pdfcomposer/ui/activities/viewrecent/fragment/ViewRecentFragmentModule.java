package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import ua.meugen.android.pdfcomposer.app.di.PerFragment;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragmentModule;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.EmptyStateImpl;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.adapters.OnPdfItemClickListener;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.presenter.ViewRecentPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.presenter.ViewRecentPresenterImpl;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.view.ViewRecentView;

@Module(includes = BaseFragmentModule.class)
public abstract class ViewRecentFragmentModule {

    @Binds @PerFragment
    abstract Fragment bindFragment(final ViewRecentFragment fragment);

    @Binds @PerFragment
    abstract ViewRecentPresenter bindPresenter(final ViewRecentPresenterImpl impl);

    @Binds @PerFragment
    abstract MvpState bindState(final EmptyStateImpl impl);

    @Binds @PerFragment
    abstract ViewRecentView bindView(final ViewRecentFragment fragment);

    @Binds @PerFragment
    abstract OnPdfItemClickListener bindPdfItemClickListener(final ViewRecentFragment fragment);
}
