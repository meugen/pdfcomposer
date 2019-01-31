package ua.meugen.android.pdfcomposer.ui.activities.createnew

import ua.meugen.android.pdfcomposer.app.di.AppComponent
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector

/**
 * @author meugen
 */
internal class CreateNewActivityInjector(private val activity: CreateNewActivity) : Injector {

    override fun inject(component: AppComponent) {
        activity.fragmentManager = activity.supportFragmentManager
    }
}
