package ua.meugen.android.pdfcomposer.app.di;

import android.arch.persistence.room.Room;
import android.content.Context;

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
        return pdfItemDao.get(new PdfItemDaoCreator(composer));
    }

    @Override
    public AppActionApi<PdfExportRequest, PdfExportProgressEvent> getPdfExportActionApi() {
        return new PdfExportActionApi(composer, getPdfItemDao());
    }

    private static class PdfItemDaoCreator implements Singleton.Creator<PdfItemDao> {

        private final Context context;

        PdfItemDaoCreator(final Context context) {
            this.context = context;
        }

        @Override
        public PdfItemDao create() {
            final AppDatabase db = createAppDatabase();
            return db.pdfItemDao();
        }

        private AppDatabase createAppDatabase() {
            return Room
                    .databaseBuilder(context, AppDatabase.class, "pdfcomposer")
                    .fallbackToDestructiveMigration()
                    .build();
        }
    }
}
