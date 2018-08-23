package ua.meugen.android.pdfcomposer.app.di;

import android.arch.persistence.room.Room;

import ua.meugen.android.pdfcomposer.app.PdfComposer;
import ua.meugen.android.pdfcomposer.model.actions.AppActionApi;
import ua.meugen.android.pdfcomposer.model.actions.PdfExportActionApi;
import ua.meugen.android.pdfcomposer.model.actions.PdfExportRequest;
import ua.meugen.android.pdfcomposer.model.db.AppDatabase;
import ua.meugen.android.pdfcomposer.model.db.dao.PdfItemDao;
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent;

/**
 * @author meugen
 */
public class AppComponentImpl implements AppComponent {

    private final PdfComposer composer;

    private final Singleton<PdfItemDao> pdfItemDao = Singleton.create(true);

    public AppComponentImpl(final PdfComposer composer) {
        this.composer = composer;
    }

    @Override
    public PdfItemDao getPdfItemDao() {
        return pdfItemDao.get(this::createPdfItemDao);
    }

    @Override
    public AppActionApi<PdfExportRequest, PdfExportProgressEvent> getPdfExportActionApi() {
        return new PdfExportActionApi(composer, getPdfItemDao());
    }

    private PdfItemDao createPdfItemDao() {
        final AppDatabase db = createAppDatabase();
        return db.pdfItemDao();
    }

    private AppDatabase createAppDatabase() {
        return Room
                .databaseBuilder(composer, AppDatabase.class, "pdfcomposer")
                .fallbackToDestructiveMigration()
                .build();
    }
}
