package ua.meugen.android.pdfcomposer.ui.activities.viewrecent

import android.content.Context
import android.content.Intent
import android.os.Bundle

import ua.meugen.android.pdfcomposer.R
import ua.meugen.android.pdfcomposer.ui.activities.base.BaseActivity
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector


class ViewRecentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_recent)
    }

    override fun createInjector(): Injector {
        return Injector.Empty()
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, ViewRecentActivity::class.java)
            context.startActivity(intent)
        }
    }
}
