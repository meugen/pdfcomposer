package ua.meugen.android.pdfcomposer.app.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;
import ua.meugen.android.pdfcomposer.app.PdfComposer;
import ua.meugen.android.pdfcomposer.app.di.qualifiers.AppContext;

@Module
public abstract class AppModule {

    @Binds
    abstract Application bindApplication(final PdfComposer pdfComposer);

    @Binds @AppContext
    abstract Context bindContext(final Application application);
}
