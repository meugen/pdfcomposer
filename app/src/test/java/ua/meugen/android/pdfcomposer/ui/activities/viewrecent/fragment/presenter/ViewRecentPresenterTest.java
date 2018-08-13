package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;
import ua.meugen.android.pdfcomposer.model.db.dao.PdfItemDao;
import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.view.ViewRecentView;
import ua.meugen.android.pdfcomposer.ui.rxloader.LifecycleHandler;

public class ViewRecentPresenterTest {

    @Mock LifecycleHandler lifecycleHandler;
    @Mock ViewRecentView view;
    @Mock PdfItemDao pdfItemDao;

    @Mock PdfItemEntity item1;
    @Mock PdfItemEntity item2;
    @Mock PdfItemEntity item3;
    @Mock PdfItemEntity item4;

    private List<PdfItemEntity> items;
    private InOrder inOrder;
    private ViewRecentPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        inOrder = Mockito.inOrder(lifecycleHandler, view, pdfItemDao);
        items = new ArrayList<>(Arrays.asList(item1, item2, item3, item4));

        ViewRecentPresenterImpl presenter = new ViewRecentPresenterImpl();
        presenter.lifecycleHandler = lifecycleHandler;
        presenter.view = view;
        presenter.pdfItemDao = pdfItemDao;
        this.presenter = presenter;
    }

    @Test
    public void testLoad() {
        Mockito.when(lifecycleHandler.load(0))
                .thenReturn(upstream -> upstream);
        Mockito.when(pdfItemDao.getAllPdfItems())
                .thenReturn(Single.just(items));

        presenter.load();

        inOrder.verify(pdfItemDao).getAllPdfItems();
        inOrder.verify(lifecycleHandler).load(0);
        inOrder.verify(view).displayItems(items);
        inOrder.verifyNoMoreInteractions();
    }
}
