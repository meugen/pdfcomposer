package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state;

import java.util.List;

import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.model.utils.CollectionUtils;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.BaseState;


public class CreateNewStateImpl extends BaseState implements CreateNewState {

    private static final String PARAM_PAGES = "pages";

    @Override
    public List<PageContent> getPages() {
        return bundle.getParcelableArrayList(PARAM_PAGES);
    }

    @Override
    public void setPages(final List<PageContent> pages) {
        bundle.putParcelableArrayList(PARAM_PAGES, CollectionUtils.toArrayList(pages));
    }
}
