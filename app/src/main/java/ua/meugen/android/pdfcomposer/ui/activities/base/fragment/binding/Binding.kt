package ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding

import android.view.View

/**
 * @author meugen
 */
interface Binding {

    fun attachView(view: View)

    fun detachView()

    operator fun <V : View> get(id: Int): V

    fun has(id: Int): Boolean
}
