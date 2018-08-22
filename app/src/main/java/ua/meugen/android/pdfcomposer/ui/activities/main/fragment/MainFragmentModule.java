package ua.meugen.android.pdfcomposer.ui.activities.main.fragment;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.EmptyPresenterImpl;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.MvpPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.EmptyStateImpl;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;
import ua.meugen.android.pdfcomposer.ui.activities.main.fragment.binding.MainBinding;
import ua.meugen.android.pdfcomposer.ui.activities.main.fragment.binding.MainBindingImpl;
import ua.meugen.android.pdfcomposer.ui.activities.main.fragment.view.MainView;

@Module(includes = BaseFragmentModule.class)
public abstract class MainFragmentModule {

    @Binds
    abstract Fragment bindFragment(final MainFragment fragment);

    @Binds
    abstract MvpPresenter<MvpState> bindMainPresenter(final EmptyPresenterImpl impl);

    @Binds
    abstract MvpState bindState(final EmptyStateImpl impl);

    @Binds
    abstract MainView bindView(final MainFragment fragment);

    @Binds
    abstract MainBinding bindBinding(MainBindingImpl impl);
}
