package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.meugen.android.pdfcomposer.model.PdfExporter;
import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state.CreateNewState;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.view.CreateNewView;

public class CreateNewPresenterTest {

    @Mock PdfExporter pdfExporter;
    @Mock CreateNewView view;
    @Mock CreateNewState state;

    @Mock PageContent content1;
    @Mock PageContent content2;
    @Mock PageContent content3;
    @Mock PageContent content4;
    @Mock PageContent content5;

    private List<PageContent> pages;
    private CreateNewPresenter presenter;
    private InOrder inOrder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        CreateNewPresenterImpl presenter = new CreateNewPresenterImpl();
        presenter.pdfExporter = pdfExporter;
        presenter.view = view;
        this.presenter = presenter;

        pages = new ArrayList<>(Arrays.asList(content1,
                content2, content3, content4, content5));
        inOrder = Mockito.inOrder(pdfExporter, view, state,
                content1, content2, content3, content4, content5);
    }

    @Test
    public void testState() {
        Mockito.when(state.getPages()).thenReturn(pages);

        presenter.onRestoreState(state);
        presenter.onSaveState(state);

        inOrder.verify(state).getPages();
        inOrder.verify(state).setPages(pages);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testSetup() {
        Mockito.when(state.getPages()).thenReturn(null);

        presenter.onRestoreState(state);
        presenter.setup();

        inOrder.verify(state).getPages();
        inOrder.verify(view).displayContent(null, null);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testSetup2() {
        Mockito.when(state.getPages()).thenReturn(pages);
        Mockito.when(content5.getImageUri()).thenReturn("content://some/content/uri");
        Mockito.when(content5.getText()).thenReturn("Some text");

        presenter.onRestoreState(state);
        presenter.setup();

        inOrder.verify(state).getPages();
        inOrder.verify(content5).getImageUri();
        inOrder.verify(content5).getText();
        inOrder.verify(view).displayContent(
                "content://some/content/uri",
                "Some text");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testImageSelected() {
        Mockito.when(state.getPages()).thenReturn(pages);
        Mockito.when(content5.getImageUri()).thenReturn(null);
        Mockito.when(content5.getText()).thenReturn("Some text");

        presenter.onRestoreState(state);
        presenter.onImageSelected("content://some/content/uri");

        inOrder.verify(state).getPages();
        inOrder.verify(content5).setImageUri("content://some/content/uri");
        inOrder.verify(view).displayContent(
                "content://some/content/uri",
                "Some text");
    }

    @Test
    public void testTextChanged() {
        Mockito.when(state.getPages()).thenReturn(pages);
        Mockito.when(content5.getImageUri()).thenReturn("content://some/content/uri");
        Mockito.when(content5.getText()).thenReturn(null);

        presenter.onRestoreState(state);
        presenter.onTextChanged("Some text");

        inOrder.verify(state).getPages();
        inOrder.verify(content5).setText("Some text");
    }

    @Test
    public void testAddPage() {
        Mockito.when(state.getPages()).thenReturn(pages);
        Mockito.when(content5.getImageUri()).thenReturn(null);
        Mockito.when(content5.getText()).thenReturn("Some text");

        presenter.onRestoreState(state);
        presenter.addPage();

        inOrder.verify(state).getPages();
        inOrder.verify(content5).getImageUri();
        inOrder.verify(view).pageDidntComplete();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testAddPage2() {
        Mockito.when(state.getPages()).thenReturn(pages);
        Mockito.when(content5.getImageUri()).thenReturn("content://some/content/uri");
        Mockito.when(content5.getText()).thenReturn(null);

        presenter.onRestoreState(state);
        presenter.addPage();

        inOrder.verify(state).getPages();
        inOrder.verify(content5).getImageUri();
        inOrder.verify(content5).getText();
        inOrder.verify(view).pageDidntComplete();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testAddPage3() {
        Mockito.when(state.getPages()).thenReturn(pages);
        Mockito.when(content5.getImageUri()).thenReturn("content://some/content/uri");
        Mockito.when(content5.getText()).thenReturn("");

        presenter.onRestoreState(state);
        presenter.addPage();

        inOrder.verify(state).getPages();
        inOrder.verify(content5).getImageUri();
        inOrder.verify(content5, Mockito.times(2)).getText();
        inOrder.verify(view).pageDidntComplete();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testAddPage4() {
        Mockito.when(state.getPages()).thenReturn(pages);
        Mockito.when(content5.getImageUri()).thenReturn("content://some/content/uri");
        Mockito.when(content5.getText()).thenReturn("Some text");

        presenter.onRestoreState(state);
        presenter.addPage();

        inOrder.verify(state).getPages();
        inOrder.verify(content5).getImageUri();
        inOrder.verify(content5, Mockito.times(2)).getText();
        inOrder.verify(view).displayContent(null, null);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testExportPages() {
        Mockito.when(state.getPages()).thenReturn(pages);
        Mockito.when(content5.getImageUri()).thenReturn(null);
        Mockito.when(content5.getText()).thenReturn("Some text");

        presenter.onRestoreState(state);
        presenter.exportPdf();

        inOrder.verify(state).getPages();
        inOrder.verify(content5).getImageUri();
        inOrder.verify(view).pageDidntComplete();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testExportPages2() {
        Mockito.when(state.getPages()).thenReturn(pages);
        Mockito.when(content5.getImageUri()).thenReturn("content://some/content/uri");
        Mockito.when(content5.getText()).thenReturn(null);

        presenter.onRestoreState(state);
        presenter.exportPdf();

        inOrder.verify(state).getPages();
        inOrder.verify(content5).getImageUri();
        inOrder.verify(content5).getText();
        inOrder.verify(view).pageDidntComplete();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testExportPage3() {
        Mockito.when(state.getPages()).thenReturn(pages);
        Mockito.when(content5.getImageUri()).thenReturn("content://some/content/uri");
        Mockito.when(content5.getText()).thenReturn("");

        presenter.onRestoreState(state);
        presenter.exportPdf();

        inOrder.verify(state).getPages();
        inOrder.verify(content5).getImageUri();
        inOrder.verify(content5, Mockito.times(2)).getText();
        inOrder.verify(view).pageDidntComplete();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testExportPage4() {
        Mockito.when(state.getPages()).thenReturn(pages);
        Mockito.when(content5.getImageUri()).thenReturn("content://some/content/uri");
        Mockito.when(content5.getText()).thenReturn("Some text");

        presenter.onRestoreState(state);
        presenter.exportPdf();

        inOrder.verify(state).getPages();
        inOrder.verify(content5).getImageUri();
        inOrder.verify(content5, Mockito.times(2)).getText();
        inOrder.verify(pdfExporter).exportPages(pages);
        inOrder.verifyNoMoreInteractions();
    }
}
