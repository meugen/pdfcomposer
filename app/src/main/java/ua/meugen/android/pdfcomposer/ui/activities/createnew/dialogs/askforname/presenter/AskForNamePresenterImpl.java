package ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.presenter;

import java.util.List;

import javax.inject.Inject;

import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.BasePresenter;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.state.AskForNameState;


public class AskForNamePresenterImpl extends BasePresenter<AskForNameState>
        implements AskForNamePresenter {

    private String name;
    private List<PageContent> pages;

    @Inject
    AskForNamePresenterImpl() {}

    @Override
    public void onSaveState(final AskForNameState state) {
        super.onSaveState(state);
        state.setPages(pages);
        state.setName(name);
    }

    @Override
    public void onRestoreState(final AskForNameState state) {
        super.onRestoreState(state);
        this.name = state.getName();
        this.pages = state.getPages();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<PageContent> getPages() {
        return this.pages;
    }

    @Override
    public void onNameChanged(final String name) {
        this.name = name;
    }
}
