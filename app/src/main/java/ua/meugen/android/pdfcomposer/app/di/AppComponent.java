package ua.meugen.android.pdfcomposer.app.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import ua.meugen.android.pdfcomposer.app.PdfComposer;
import ua.meugen.android.pdfcomposer.app.di.modules.ActivitiesModule;
import ua.meugen.android.pdfcomposer.app.di.modules.AppModule;
import ua.meugen.android.pdfcomposer.app.di.modules.DbModule;

@Singleton
@Component(modules = {AppModule.class, ActivitiesModule.class,
        DbModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<PdfComposer> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<PdfComposer> {}
}
