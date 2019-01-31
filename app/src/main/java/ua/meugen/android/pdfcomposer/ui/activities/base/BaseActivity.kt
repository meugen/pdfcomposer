package ua.meugen.android.pdfcomposer.ui.activities.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import ua.meugen.android.pdfcomposer.app.PdfComposer

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    private fun inject() {
        val injector = createInjector()
        injector.inject(PdfComposer.from(this).appComponent)
    }

    protected abstract fun createInjector(): Injector
}
