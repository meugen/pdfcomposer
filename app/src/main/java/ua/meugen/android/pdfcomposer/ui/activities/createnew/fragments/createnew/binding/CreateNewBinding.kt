package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.binding

import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding.Binding
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.view.CreateNewView

/**
 * @author meugen
 */
interface CreateNewBinding : Binding {

    fun setupListeners(view: CreateNewView)

    fun displayContent(imageUri: String?, text: String?)
}
