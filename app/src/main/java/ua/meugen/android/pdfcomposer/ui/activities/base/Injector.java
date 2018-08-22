package ua.meugen.android.pdfcomposer.ui.activities.base;

import android.support.annotation.MainThread;
import android.support.v4.app.Fragment;

import ua.meugen.android.pdfcomposer.app.di.AppComponent;
import ua.meugen.android.pdfcomposer.ui.rxloader.LifecycleHandler;
import ua.meugen.android.pdfcomposer.ui.rxloader.LoaderLifecycleHandler;

/**
 * @author meugen
 */
public interface Injector {

    Injector EMPTY = c -> {};

    @MainThread
    void inject(AppComponent component);

    static LifecycleHandler provideLifecycleHandler(
            final Fragment fragment) {
        return new LoaderLifecycleHandler(
                fragment.getContext(),
                fragment.getLoaderManager());
    }
}
