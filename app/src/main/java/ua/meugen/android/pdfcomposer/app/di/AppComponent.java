package ua.meugen.android.pdfcomposer.app.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import ua.meugen.android.pdfcomposer.app.PdfComposer;
import ua.meugen.android.pdfcomposer.app.di.modules.AppModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent extends AndroidInjector<PdfComposer> {

    @Component.Builder
    public abstract class Builder extends AndroidInjector.Builder<PdfComposer> {}
}
