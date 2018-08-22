package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.presenter;

import java.util.ArrayList;
import java.util.List;

import ua.meugen.android.pdfcomposer.model.PdfExporter;
import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.BasePresenter;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state.CreateNewState;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.view.CreateNewView;


public class CreateNewPresenterImpl extends BasePresenter<CreateNewState>
        implements CreateNewPresenter {

    private final CreateNewView view;
    private final PdfExporter pdfExporter;

    private List<PageContent> pages;

    public CreateNewPresenterImpl(
            final CreateNewView view,
            final PdfExporter pdfExporter) {
        this.view = view;
        this.pdfExporter = pdfExporter;
    }

    @Override
    public void onSaveState(final CreateNewState state) {
        super.onSaveState(state);
        state.setPages(pages);
    }

    @Override
    public void onRestoreState(final CreateNewState state) {
        super.onRestoreState(state);
        this.pages = state.getPages();
    }

    private PageContent getLastPage() {
        return pages.get(pages.size() - 1);
    }

    @Override
    public void setup() {
        if (pages == null) {
            pages = new ArrayList<>();
            pages.add(new PageContent());
        }
        final PageContent lastPage = getLastPage();
        view.displayContent(lastPage.getImageUri(), lastPage.getText());
    }

    @Override
    public void onImageSelected(final String imageUri) {
        final PageContent lastPage = getLastPage();
        lastPage.setImageUri(imageUri);
        view.displayContent(imageUri, lastPage.getText());
    }

    @Override
    public void onTextChanged(final String text) {
        final PageContent lastPage = getLastPage();
        lastPage.setText(text);
    }

    private boolean checkLastPageValid() {
        final PageContent lastPage = getLastPage();
        return lastPage.getImageUri() != null
                && lastPage.getText() != null
                && !lastPage.getText().isEmpty();
    }

    @Override
    public void addPage() {
        if (!checkLastPageValid()) {
            view.pageDidntComplete();
            return;
        }
        pages.add(new PageContent());
        view.displayContent(null, null);
    }

    @Override
    public void exportPdf() {
        if (!checkLastPageValid()) {
            view.pageDidntComplete();
            return;
        }
        pdfExporter.exportPages(pages);
    }
}
