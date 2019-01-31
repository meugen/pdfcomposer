package ua.meugen.android.pdfcomposer.ui.activities.main.fragment.binding

import android.view.View

import java.lang.ref.WeakReference

import ua.meugen.android.pdfcomposer.R
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding.BaseBinding
import ua.meugen.android.pdfcomposer.ui.activities.main.fragment.view.MainView

/**
 * @author meugen
 */
class MainBindingImpl : BaseBinding(), MainBinding {

    override fun setupListeners(view: MainView) {
        val impl = OnClickListenerImpl(view)
        get<View>(R.id.create_new_document).setOnClickListener(impl)
        get<View>(R.id.view_recent_documents).setOnClickListener(impl)
    }

    private class OnClickListenerImpl internal constructor(view: MainView) : View.OnClickListener {

        private val viewRef: WeakReference<MainView>

        init {
            viewRef = WeakReference(view)
        }

        override fun onClick(v: View) {
            val view = viewRef.get() ?: return
            val id = v.id
            if (id == R.id.create_new_document) {
                view.createNewDocument()
            } else if (id == R.id.view_recent_documents) {
                view.viewRecentDocuments()
            }
        }
    }
}
