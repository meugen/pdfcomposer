package ua.meugen.android.pdfcomposer.ui.activities.main

import android.content.Context
import android.content.Intent
import android.os.Bundle

import ua.meugen.android.pdfcomposer.R
import ua.meugen.android.pdfcomposer.ui.activities.base.BaseActivity
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.ViewRecentActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (intent.getBooleanExtra(EXTRA_OPEN_RECENT_PDFS, false)) {
            ViewRecentActivity.start(this)
        }
    }

    override fun createInjector(): Injector {
        return Injector.Empty()
    }

    companion object {

        private const val EXTRA_OPEN_RECENT_PDFS = "openRecentPdfs"

        fun start(context: Context, openRecentPdfs: Boolean) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(EXTRA_OPEN_RECENT_PDFS, openRecentPdfs)
            context.startActivity(intent)
        }
    }
}
