package ua.meugen.android.pdfcomposer.app.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.meugen.android.pdfcomposer.model.db.AppDatabase;
import ua.meugen.android.pdfcomposer.model.db.dao.PdfItemDao;

@Module
public abstract class DbModule {

    @Provides @Singleton
    static AppDatabase provideAppDatabase(
            @Named(AppModule.APP_CONTEXT) final Context context) {
        return Room
                .databaseBuilder(context, AppDatabase.class, "pdfcomposer")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides @Singleton
    static PdfItemDao providePdfItemDao(final AppDatabase db) {
        return db.pdfItemDao();
    }
}
