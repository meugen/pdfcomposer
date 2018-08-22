package ua.meugen.android.pdfcomposer.ui.activities.createnew;

import ua.meugen.android.pdfcomposer.app.di.AppComponent;
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector;

/**
 * @author meugen
 */
class CreateNewActivityInjector implements Injector {

    private final CreateNewActivity activity;

    CreateNewActivityInjector(final CreateNewActivity activity) {
        this.activity = activity;
    }

    @Override
    public void inject(final AppComponent component) {
        activity.fragmentManager = activity.getSupportFragmentManager();
    }
}
