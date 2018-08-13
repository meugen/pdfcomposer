package ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.state;

import java.util.List;

import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;


public interface AskForNameState extends MvpState {

    String PARAM_NAME = "name";
    String PARAM_PAGES = "pages";

    String getName();

    void setName(String name);

    List<PageContent> getPages();

    void setPages(List<PageContent> pages);
}
