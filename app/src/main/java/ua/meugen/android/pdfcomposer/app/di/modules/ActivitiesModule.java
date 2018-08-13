package ua.meugen.android.pdfcomposer.app.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.CreateNewActivity;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.CreateNewActivityModule;
import ua.meugen.android.pdfcomposer.ui.activities.main.MainActivity;
import ua.meugen.android.pdfcomposer.ui.activities.main.MainActivityModule;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.ViewRecentActivity;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.ViewRecentActivityModule;

@Module
public abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = CreateNewActivityModule.class)
    abstract CreateNewActivity contributeCreateNewActivity();

    @ContributesAndroidInjector(modules = ViewRecentActivityModule.class)
    abstract ViewRecentActivity contributeViewRecentActivity();
}
