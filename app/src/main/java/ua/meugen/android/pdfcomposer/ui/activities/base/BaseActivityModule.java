package ua.meugen.android.pdfcomposer.ui.activities.base;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import ua.meugen.android.pdfcomposer.app.di.PerActivity;

@Module
public abstract class BaseActivityModule {

    public static final String ACTIVITY_CONTEXT = "activityContext";

    @Binds @Named(ACTIVITY_CONTEXT) @PerActivity
    abstract Context bindContext(final AppCompatActivity activity);

    @Provides @PerActivity
    static FragmentManager provideFragmentManager(
            final AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
