package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import ua.meugen.android.pdfcomposer.model.PdfExporter;
import ua.meugen.android.pdfcomposer.model.actions.AppActionApi;
import ua.meugen.android.pdfcomposer.model.actions.PdfExportRequest;
import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state.ProgressState;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.view.ProgressView;
import ua.meugen.android.pdfcomposer.ui.rxloader.LifecycleHandler;

public class ProgressPresenterTest {

    @Mock PdfExporter pdfExporter;
    @Mock AppActionApi<PdfExportRequest, PdfExportProgressEvent> action;
    @Mock LifecycleHandler lifecycleHandler;
    @Mock ProgressView view;
    @Mock ProgressState state;

    @Mock PageContent content1;
    @Mock PageContent content2;
    @Mock PageContent content3;
    @Mock PageContent content4;
    @Mock PageContent content5;

    private List<PageContent> pages;
    private InOrder inOrder;
    private ProgressPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        pages = new ArrayList<>(Arrays.asList(content1,
                content2, content3, content4, content5));
        inOrder = Mockito.inOrder(pdfExporter, action,
                lifecycleHandler, view, state, content1,
                content2, content3, content4, content5);

        ProgressPresenterImpl presenter = new ProgressPresenterImpl();
        presenter.pdfExporter = pdfExporter;
        presenter.action = action;
        presenter.lifecycleHandler = lifecycleHandler;
        presenter.view = view;
        this.presenter = presenter;
    }

    @Test
    public void testState() {
        Mockito.when(state.getName()).thenReturn("Name");
        Mockito.when(state.getPages()).thenReturn(pages);

        presenter.onRestoreState(state);
        presenter.onSaveState(state);

        inOrder.verify(state).getPages();
        inOrder.verify(state).getName();
        inOrder.verify(state).setPages(pages);
        inOrder.verify(state).setName("Name");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testStart() {
        Mockito.when(state.getName()).thenReturn("Name");
        Mockito.when(state.getPages()).thenReturn(pages);
        Observable<PdfExportProgressEvent> result = Observable.just(
                new PdfExportProgressEvent(0, 5),
                new PdfExportProgressEvent(1, 5),
                new PdfExportProgressEvent(2, 5),
                new PdfExportProgressEvent(3, 5),
                new PdfExportProgressEvent(4, 5),
                new PdfExportProgressEvent(5, 5));
        Mockito.when(action.action(new PdfExportRequest("Name", pages)))
                .thenReturn(result);
        Mockito.when(lifecycleHandler.load(0))
                .thenReturn(upstream -> upstream);

        presenter.onRestoreState(state);
        presenter.start();

        inOrder.verify(action).action(new PdfExportRequest("Name", pages));
        inOrder.verify(lifecycleHandler).load(0);
        inOrder.verify(view).onUpdateProgress(
                new PdfExportProgressEvent(0, 5));
        inOrder.verify(view).onUpdateProgress(
                new PdfExportProgressEvent(1, 5));
        inOrder.verify(view).onUpdateProgress(
                new PdfExportProgressEvent(2, 5));
        inOrder.verify(view).onUpdateProgress(
                new PdfExportProgressEvent(3, 5));
        inOrder.verify(view).onUpdateProgress(
                new PdfExportProgressEvent(4, 5));
        inOrder.verify(view).onUpdateProgress(
                new PdfExportProgressEvent(5, 5));
        inOrder.verify(pdfExporter).onPdfExportSuccess();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testStart2() {
        Mockito.when(state.getName()).thenReturn("Name");
        Mockito.when(state.getPages()).thenReturn(pages);
        Exception exception = new IllegalArgumentException();
        Mockito.when(action.action(new PdfExportRequest("Name", pages)))
                .thenReturn(Observable.error(exception));
        Mockito.when(lifecycleHandler.load(0))
                .thenReturn(upstream -> upstream);

        presenter.onRestoreState(state);
        presenter.start();

        inOrder.verify(action).action(new PdfExportRequest("Name", pages));
        inOrder.verify(lifecycleHandler).load(0);
        inOrder.verify(pdfExporter).onPdfExportError(exception);
        inOrder.verifyNoMoreInteractions();
    }
}
