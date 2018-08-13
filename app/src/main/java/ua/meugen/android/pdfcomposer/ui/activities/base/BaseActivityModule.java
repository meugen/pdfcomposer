package ua.meugen.android.pdfcomposer.ui.activities.base;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import ua.meugen.android.pdfcomposer.app.di.qualifiers.ActivityContext;

@Module
public abstract class BaseActivityModule {

    @Binds @ActivityContext
    abstract Context bindContext(final AppCompatActivity activity);

    @Provides
    static FragmentManager provideFragmentManager(
            final AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
