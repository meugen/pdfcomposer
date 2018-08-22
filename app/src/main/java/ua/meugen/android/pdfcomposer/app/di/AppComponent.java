package ua.meugen.android.pdfcomposer.app.di;

import android.support.annotation.MainThread;

import ua.meugen.android.pdfcomposer.model.actions.AppActionApi;
import ua.meugen.android.pdfcomposer.model.actions.PdfExportRequest;
import ua.meugen.android.pdfcomposer.model.db.dao.PdfItemDao;
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent;
import ua.meugen.android.pdfcomposer.ui.rxloader.LifecycleHandler;

public interface AppComponent {

    @MainThread
    PdfItemDao getPdfItemDao();

    AppActionApi<PdfExportRequest, PdfExportProgressEvent> getPdfExportActionApi();
}
