package ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragmentModule;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.presenter.AskForNamePresenter;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.presenter.AskForNamePresenterImpl;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.state.AskForNameState;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.state.AskForNameStateImpl;

@Module(includes = BaseFragmentModule.class)
public abstract class AskForNameDialogModule {

    @Binds
    abstract Fragment bindFragment(final AskForNameDialog dialog);

    @Binds
    abstract AskForNamePresenter bindPresenter(final AskForNamePresenterImpl impl);

    @Binds
    abstract AskForNameState bindState(final AskForNameStateImpl impl);
}
