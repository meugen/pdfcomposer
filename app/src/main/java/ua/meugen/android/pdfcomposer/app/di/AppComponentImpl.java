package ua.meugen.android.pdfcomposer.app.di;

import android.arch.persistence.room.Room;

import java.lang.ref.SoftReference;

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

    private final SoftSingleton<PdfItemDao> pdfItemDao = new SoftSingleton<>();

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

    private static class SoftSingleton<T> {

        private SoftReference<T> ref;

        public T get(final Creator<T> creator) {
            T result = ref == null ? null : ref.get();
            if (result == null) {
                result = creator.create();
                ref = new SoftReference<>(result);
            }
            return result;
        }
    }

    private interface Creator<T> {

        T create();
    }
}
