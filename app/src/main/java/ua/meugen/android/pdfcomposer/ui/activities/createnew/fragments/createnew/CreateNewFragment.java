package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragment;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.binding.CreateNewBinding;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.presenter.CreateNewPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state.CreateNewState;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.view.CreateNewView;


public class CreateNewFragment extends BaseFragment<CreateNewState, CreateNewPresenter, CreateNewBinding>
        implements CreateNewView {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_new,
                container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull final View view,
            @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setupListeners(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setup();
    }

    @Override
    public void loadImage() {
        final Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    public void addPage() {
        presenter.addPage();
    }

    @Override
    public void textChanged(final CharSequence text) {
        presenter.onTextChanged(text.toString());
    }

    @Override
    public void exportToPdf() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            presenter.exportPdf();
        } else {
            requestPermissions(
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    0);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            final int requestCode,
            @NonNull final String[] permissions,
            @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            presenter.exportPdf();
        }
    }

    @Override
    public void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null) {
            return;
        }
        final Uri imageUri = data.getData();
        if (imageUri != null) {
            presenter.onImageSelected(imageUri.toString());
        }
    }

    @Override
    public void displayContent(final String imageUri, final String text) {
        binding.displayContent(imageUri, text);
    }

    @Override
    public void pageDidntComplete() {
        Snackbar.make(binding.get(R.id.container),
                R.string.message_page_didnt_complete,
                Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected Injector createInjector() {
        return new CreateNewFragmentInjector(this);
    }
}
