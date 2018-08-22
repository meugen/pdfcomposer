package ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname;

import ua.meugen.android.pdfcomposer.app.di.AppComponent;
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.presenter.AskForNamePresenterImpl;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.state.AskForNameStateImpl;

public class AskForNameDialogInjector implements Injector {

    private final AskForNameDialog dialog;

    public AskForNameDialogInjector(final AskForNameDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void inject(final AppComponent component) {
        dialog.state = new AskForNameStateImpl();
        dialog.presenter = new AskForNamePresenterImpl();
    }
}
