package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state;

import java.util.List;

import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;


public interface ProgressState extends MvpState {

    String PARAM_NAME = "name";
    String PARAM_PAGES = "pages";

    String getName();

    void setName(String name);

    List<PageContent> getPages();

    void setPages(List<PageContent> pages);
}
