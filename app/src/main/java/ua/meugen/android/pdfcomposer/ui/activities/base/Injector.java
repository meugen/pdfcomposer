package ua.meugen.android.pdfcomposer.ui.activities.base;

import ua.meugen.android.pdfcomposer.app.di.AppComponent;

/**
 * @author meugen
 */
public interface Injector {

    Injector EMPTY = c -> {};

    void inject(AppComponent component);
}
