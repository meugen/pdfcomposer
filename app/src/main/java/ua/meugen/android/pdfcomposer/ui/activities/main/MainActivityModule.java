package ua.meugen.android.pdfcomposer.ui.activities.main;

import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ua.meugen.android.pdfcomposer.ui.activities.main.fragment.MainFragment;
import ua.meugen.android.pdfcomposer.ui.activities.main.fragment.MainFragmentModule;

@Module(includes = BaseActivityModule.class)
public abstract class MainActivityModule {

    @Binds
    abstract AppCompatActivity bindAppCompatActivity(final MainActivity activity);

    @ContributesAndroidInjector(modules = MainFragmentModule.class)
    abstract MainFragment contributeMainFragment();
}
