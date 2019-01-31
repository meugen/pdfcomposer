package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.binding

import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView

import java.lang.ref.WeakReference

import ua.meugen.android.pdfcomposer.R
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding.BaseBinding
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.view.CreateNewView

/**
 * @author meugen
 */
class CreateNewBindingImpl : BaseBinding(), CreateNewBinding {

    override fun setupListeners(view: CreateNewView) {
        val listener = OnClickListenerImpl(view)
        get<View>(R.id.export_to_pdf).setOnClickListener(listener)
        get<View>(R.id.load_image).setOnClickListener(listener)
        get<View>(R.id.add_page).setOnClickListener(listener)

        val watcher = TextWatcherImpl(view)
        this.get<EditText>(R.id.text).addTextChangedListener(watcher)
    }

    override fun displayContent(imageUri: String?, text: String?) {
        val imageView = get<ImageView>(R.id.image)
        if (imageUri == null) {
            imageView.setImageResource(R.drawable.ic_photo_size_select_actual_black_48dp)
        } else {
            imageView.setImageURI(Uri.parse(imageUri))
        }
        val editText = get<EditText>(R.id.text)
        editText.setText(text)
    }

    private class OnClickListenerImpl internal constructor(view: CreateNewView) : View.OnClickListener {

        private val viewRef: WeakReference<CreateNewView>

        init {
            this.viewRef = WeakReference(view)
        }

        override fun onClick(v: View) {
            val view = viewRef.get() ?: return
            val id = v.id
            if (id == R.id.export_to_pdf) {
                view.exportToPdf()
            } else if (id == R.id.load_image) {
                view.loadImage()
            } else if (id == R.id.add_page) {
                view.addPage()
            }
        }
    }

    private class TextWatcherImpl internal constructor(view: CreateNewView) : TextWatcher {

        private val viewRef: WeakReference<CreateNewView>

        init {
            this.viewRef = WeakReference(view)
        }

        override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int) {
        }

        override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            val view = viewRef.get() ?: return
            view.textChanged(s)
        }
    }
}
