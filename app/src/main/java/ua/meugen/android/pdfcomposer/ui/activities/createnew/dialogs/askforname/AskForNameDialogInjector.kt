package ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname

import ua.meugen.android.pdfcomposer.app.di.AppComponent
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.presenter.AskForNamePresenterImpl
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.state.AskForNameStateImpl

class AskForNameDialogInjector(private val dialog: AskForNameDialog) : Injector {

    override fun inject(component: AppComponent) {
        dialog.state = AskForNameStateImpl()
        dialog.presenter = AskForNamePresenterImpl()
    }
}
