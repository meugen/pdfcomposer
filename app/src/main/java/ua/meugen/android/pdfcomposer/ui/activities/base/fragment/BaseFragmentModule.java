package ua.meugen.android.pdfcomposer.ui.activities.base.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import ua.meugen.android.pdfcomposer.app.di.PerFragment;
import ua.meugen.android.pdfcomposer.ui.rxloader.LifecycleHandler;
import ua.meugen.android.pdfcomposer.ui.rxloader.LoaderLifecycleHandler;

@Module
public abstract class BaseFragmentModule {

    @Binds @PerFragment
    abstract LifecycleHandler bindLifecycleHandler(final LoaderLifecycleHandler handler);

    @Provides @PerFragment
    static LoaderManager provideLoaderManager(final Fragment fragment) {
        return fragment.getLoaderManager();
    }
}
