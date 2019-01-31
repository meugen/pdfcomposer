package ua.meugen.android.pdfcomposer.ui.activities.base

import android.support.annotation.MainThread
import android.support.v4.app.Fragment

import ua.meugen.android.pdfcomposer.app.di.AppComponent
import ua.meugen.android.pdfcomposer.ui.rxloader.LifecycleHandler
import ua.meugen.android.pdfcomposer.ui.rxloader.LoaderLifecycleHandler

/**
 * @author meugen
 */
interface Injector {

    @MainThread
    fun inject(component: AppComponent)

    class Empty : Injector {

        override fun inject(component: AppComponent) {}
    }

    companion object {

        fun provideLifecycleHandler(
                fragment: Fragment): LifecycleHandler {
            return LoaderLifecycleHandler(
                    fragment.context!!,
                    fragment.loaderManager)
        }
    }
}
