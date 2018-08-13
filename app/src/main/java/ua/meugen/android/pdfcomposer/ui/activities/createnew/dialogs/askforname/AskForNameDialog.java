package ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.model.utils.CollectionUtils;
import ua.meugen.android.pdfcomposer.ui.activities.base.BaseActivityModule;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseDialogFragment;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.presenter.AskForNamePresenter;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.state.AskForNameState;


public class AskForNameDialog extends BaseDialogFragment<AskForNameState, AskForNamePresenter>
        implements DialogInterface.OnClickListener {

    public static AskForNameDialog build(final List<PageContent> pages) {
        final Bundle args = new Bundle();
        args.putParcelableArrayList(AskForNameState.PARAM_PAGES,
                CollectionUtils.toArrayList(pages));

        final AskForNameDialog dialog = new AskForNameDialog();
        dialog.setArguments(args);
        return dialog;
    }

    @Inject @Named(BaseActivityModule.ACTIVITY_CONTEXT)
    Context context;
    @Inject AskForNameListener listener;

    private AlertDialog alertDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final EditText editText = new EditText(context);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setText(presenter.getName());
        editText.addTextChangedListener(new TextWatcherImpl());

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.title_name_for_document);
        builder.setView(editText);
        builder.setPositiveButton(R.string.button_ok, this);
        builder.setNegativeButton(R.string.button_cancel, this);
        alertDialog = builder.create();

        return alertDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        updatePositiveButtonEnabled(presenter.getName());
    }

    @Override
    public void onClick(final DialogInterface dialog, final int which) {
        if (which == AlertDialog.BUTTON_POSITIVE) {
            listener.onDocumentNamed(presenter.getName(), presenter.getPages());
        }
    }

    private void updatePositiveButtonEnabled(final String name) {
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setEnabled(name != null && name.trim().length() > 0);
    }

    private class TextWatcherImpl implements TextWatcher {

        @Override
        public void beforeTextChanged(
                final CharSequence s,
                final int start,
                final int count,
                final int after) {}

        @Override
        public void onTextChanged(
                final CharSequence s,
                final int start,
                final int before,
                final int count) {}

        @Override
        public void afterTextChanged(final Editable s) {
            presenter.onNameChanged(s.toString());
            updatePositiveButtonEnabled(s.toString());
        }
    }
}
