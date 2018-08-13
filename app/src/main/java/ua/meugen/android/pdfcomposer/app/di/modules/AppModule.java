package ua.meugen.android.pdfcomposer.app.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;
import ua.meugen.android.pdfcomposer.app.PdfComposer;

@Module(includes = {
        AndroidSupportInjectionModule.class,
        ActivitiesModule.class, DbModule.class})
public abstract class AppModule {

    public static final String APP_CONTEXT = "appContext";

    @Binds @Singleton
    abstract Application bindApplication(final PdfComposer pdfComposer);

    @Binds @Named(APP_CONTEXT) @Singleton
    abstract Context bindContext(final Application application);
}
