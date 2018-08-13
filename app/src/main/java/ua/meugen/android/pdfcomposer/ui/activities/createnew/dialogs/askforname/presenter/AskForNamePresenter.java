package ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.presenter;

import java.util.List;

import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.MvpPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.state.AskForNameState;


public interface AskForNamePresenter extends MvpPresenter<AskForNameState> {

    String getName();

    List<PageContent> getPages();

    void onNameChanged(String name);
}
