package ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname;

import java.util.List;

import ua.meugen.android.pdfcomposer.model.data.PageContent;

public interface AskForNameListener {

    void onDocumentNamed(String name, List<PageContent> pages);
}
