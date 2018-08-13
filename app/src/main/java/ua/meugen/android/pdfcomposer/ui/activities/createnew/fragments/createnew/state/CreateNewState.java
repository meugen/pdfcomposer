package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state;

import java.util.List;

import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;


public interface CreateNewState extends MvpState {

    List<PageContent> getPages();

    void setPages(List<PageContent> pages);
}
