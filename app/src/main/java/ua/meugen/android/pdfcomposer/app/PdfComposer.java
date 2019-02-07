package ua.meugen.android.pdfcomposer.app;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import ua.meugen.android.pdfcomposer.app.di.AppComponent;
import ua.meugen.android.pdfcomposer.model.actions.AppActionApi;
import ua.meugen.android.pdfcomposer.model.actions.PdfExportActionApi;
import ua.meugen.android.pdfcomposer.model.actions.PdfExportRequest;
import ua.meugen.android.pdfcomposer.model.db.AppDatabase;
import ua.meugen.android.pdfcomposer.model.db.dao.PdfItemDao;
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent;

public class PdfComposer extends Application implements AppComponent {

    public static AppComponent appComponent(final Context context) {
        return (AppComponent) context.getApplicationContext();
    }

    private PdfItemDao pdfItemDao;

    private AppDatabase createAppDatabase() {
        return Room
                .databaseBuilder(this, AppDatabase.class, "pdfcomposer")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Override
    public PdfItemDao getPdfItemDao() {
        if (pdfItemDao == null) {
            pdfItemDao = createAppDatabase().pdfItemDao();
        }
        return pdfItemDao;
    }

    @Override
    public AppActionApi<PdfExportRequest, PdfExportProgressEvent> getPdfExportActionApi() {
        return new PdfExportActionApi(this, getPdfItemDao());
    }
}
