package ua.meugen.android.pdfcomposer.ui.activities.createnew

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log

import ua.meugen.android.pdfcomposer.R
import ua.meugen.android.pdfcomposer.model.PdfExporter
import ua.meugen.android.pdfcomposer.model.data.PageContent
import ua.meugen.android.pdfcomposer.ui.activities.base.BaseActivity
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.AskForNameDialog
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.AskForNameListener
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.CreateNewFragment
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.ProgressFragment
import ua.meugen.android.pdfcomposer.ui.activities.main.MainActivity


class CreateNewActivity : BaseActivity(), PdfExporter, AskForNameListener {

    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new)

        if (savedInstanceState == null) {
            val fragment = CreateNewFragment()
            fragmentManager.beginTransaction()
                    .add(R.id.container, fragment, TAG_CREATE_NEW)
                    .commit()
        }
    }

    override fun onDocumentNamed(name: String, pages: List<PageContent>) {
        val createNewFragment = fragmentManager.findFragmentByTag(TAG_CREATE_NEW)
        fragmentManager.beginTransaction()
                .hide(createNewFragment)
                .add(R.id.container, ProgressFragment.build(name, pages), TAG_PROGRESS)
                .commit()
    }

    override fun exportPages(pages: List<PageContent>) {
        AskForNameDialog.build(pages).show(fragmentManager, "ask-for-name")
    }

    override fun onPdfExportSuccess() {
        MainActivity.start(this, true)
    }

    override fun onPdfExportError(th: Throwable) {
        Log.d(TAG, th.message, th)

        val createNewFragment = fragmentManager.findFragmentByTag(TAG_CREATE_NEW)
        val progressFragment = fragmentManager.findFragmentByTag(TAG_PROGRESS)
        fragmentManager.beginTransaction()
                .remove(progressFragment)
                .show(createNewFragment)
                .commit()
    }

    override fun createInjector(): Injector {
        return CreateNewActivityInjector(this)
    }

    companion object {

        private val TAG = CreateNewActivity::class.java.simpleName

        private const val TAG_CREATE_NEW = "createNew"
        private const val TAG_PROGRESS = "progress"

        fun start(context: Context) {
            val intent = Intent(context, CreateNewActivity::class.java)
            context.startActivity(intent)
        }
    }
}
