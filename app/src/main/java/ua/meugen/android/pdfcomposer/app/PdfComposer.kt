package ua.meugen.android.pdfcomposer.app

import android.app.Application
import android.content.Context

import ua.meugen.android.pdfcomposer.app.di.AppComponent
import ua.meugen.android.pdfcomposer.app.di.AppComponentImpl

class PdfComposer : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponentImpl(this)
    }

    companion object {

        fun from(context: Context): PdfComposer {
            return context.applicationContext as PdfComposer
        }
    }
}
