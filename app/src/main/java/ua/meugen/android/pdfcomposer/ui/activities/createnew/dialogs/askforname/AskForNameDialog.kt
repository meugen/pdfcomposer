package ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText

import ua.meugen.android.pdfcomposer.R
import ua.meugen.android.pdfcomposer.model.data.PageContent
import ua.meugen.android.pdfcomposer.model.utils.toArrayList
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseDialogFragment
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.presenter.AskForNamePresenter
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.state.AskForNameState


class AskForNameDialog : BaseDialogFragment<AskForNameState, AskForNamePresenter>(), DialogInterface.OnClickListener {

    private var alertDialog: AlertDialog? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = context

        val editText = EditText(context)
        editText.inputType = InputType.TYPE_CLASS_TEXT
        editText.setText(presenter!!.name)
        editText.addTextChangedListener(TextWatcherImpl())

        val builder = AlertDialog.Builder(context!!)
        builder.setTitle(R.string.title_name_for_document)
        builder.setView(editText)
        builder.setPositiveButton(R.string.button_ok, this)
        builder.setNegativeButton(R.string.button_cancel, this)
        alertDialog = builder.create()

        return alertDialog!!
    }

    override fun onStart() {
        super.onStart()
        updatePositiveButtonEnabled(presenter!!.name)
    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        if (which == AlertDialog.BUTTON_POSITIVE) {
            val listener = context as AskForNameListener?
            listener!!.onDocumentNamed(presenter.name!!, presenter.pages!!)
        }
    }

    private fun updatePositiveButtonEnabled(name: String?) {
        alertDialog!!.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = name != null && name.trim { it <= ' ' }.length > 0
    }

    override fun createInjector(): Injector {
        return AskForNameDialogInjector(this)
    }

    private inner class TextWatcherImpl : TextWatcher {

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
            presenter!!.onNameChanged(s.toString())
            updatePositiveButtonEnabled(s.toString())
        }
    }

    companion object {

        fun build(pages: List<PageContent>): AskForNameDialog {
            val args = Bundle()
            args.putParcelableArrayList(AskForNameState.PARAM_PAGES,
                    pages.toArrayList)

            val dialog = AskForNameDialog()
            dialog.arguments = args
            return dialog
        }
    }
}