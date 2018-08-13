package ua.meugen.android.pdfcomposer.ui.activities.viewrecent;

import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ua.meugen.android.pdfcomposer.app.di.PerActivity;
import ua.meugen.android.pdfcomposer.app.di.PerFragment;
import ua.meugen.android.pdfcomposer.ui.activities.base.BaseActivityModule;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.ViewRecentFragment;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.ViewRecentFragmentModule;

@Module(includes = BaseActivityModule.class)
public abstract class ViewRecentActivityModule {

    @Binds @PerActivity
    abstract AppCompatActivity bindAppCompatActivity(final ViewRecentActivity activity);

    @ContributesAndroidInjector(modules = ViewRecentFragmentModule.class)
    @PerFragment
    abstract ViewRecentFragment contributeViewRecentFragment();
}