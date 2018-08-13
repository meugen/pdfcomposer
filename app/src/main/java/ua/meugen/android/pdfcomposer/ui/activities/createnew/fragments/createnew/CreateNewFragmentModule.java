package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragmentModule;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.binding.CreateNewBinding;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.binding.CreateNewBindingImpl;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.presenter.CreateNewPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.presenter.CreateNewPresenterImpl;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state.CreateNewState;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state.CreateNewStateImpl;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.view.CreateNewView;

@Module(includes = BaseFragmentModule.class)
public abstract class CreateNewFragmentModule {

    @Binds
    abstract Fragment bindFragment(final CreateNewFragment fragment);

    @Binds
    abstract CreateNewPresenter bindPresenter(final CreateNewPresenterImpl impl);

    @Binds
    abstract CreateNewState bindState(final CreateNewStateImpl impl);

    @Binds
    abstract CreateNewView bindView(final CreateNewFragment fragment);

    @Binds
    abstract CreateNewBinding bindBinding(final CreateNewBindingImpl impl);
}
