package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ua.meugen.android.pdfcomposer.R
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragment
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.binding.CreateNewBinding
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.presenter.CreateNewPresenter
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state.CreateNewState
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.view.CreateNewView


class CreateNewFragment : BaseFragment<CreateNewState, CreateNewPresenter, CreateNewBinding>(), CreateNewView {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_new,
                container, false)
    }

    override fun onViewCreated(
            view: View,
            savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setupListeners(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.setup()
    }

    override fun loadImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
    }

    override fun addPage() {
        presenter.addPage()
    }

    override fun textChanged(text: CharSequence) {
        presenter.onTextChanged(text.toString())
    }

    override fun exportToPdf() {
        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            presenter.exportPdf()
        } else {
            requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    0)
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            presenter.exportPdf()
        }
    }

    override fun onActivityResult(
            requestCode: Int,
            resultCode: Int,
            data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK || data == null) {
            return
        }
        val imageUri = data.data
        if (imageUri != null) {
            presenter.onImageSelected(imageUri.toString())
        }
    }

    override fun displayContent(imageUri: String?, text: String?) {
        binding.displayContent(imageUri, text)
    }

    override fun pageDidntComplete() {
        Snackbar.make(binding[R.id.container],
                R.string.message_page_didnt_complete,
                Snackbar.LENGTH_LONG).show()
    }

    override fun createInjector(): Injector {
        return CreateNewFragmentInjector(this)
    }
}
