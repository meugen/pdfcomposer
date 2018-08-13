package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state;

import java.util.List;

import javax.inject.Inject;

import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.model.utils.CollectionUtils;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.BaseState;


public class ProgressStateImpl extends BaseState implements ProgressState {

    @Inject
    ProgressStateImpl() {}

    @Override
    public String getName() {
        return bundle.getString(PARAM_NAME);
    }

    @Override
    public void setName(final String name) {
        bundle.putString(PARAM_NAME, name);
    }

    @Override
    public List<PageContent> getPages() {
        return bundle.getParcelableArrayList(PARAM_PAGES);
    }

    @Override
    public void setPages(final List<PageContent> pages) {
        bundle.putParcelableArrayList(PARAM_PAGES, CollectionUtils.toArrayList(pages));
    }
}
