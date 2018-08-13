package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;
import javax.inject.Named;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.app.di.qualifiers.ActivityContext;
import ua.meugen.android.pdfcomposer.databinding.FragmentCreateNewBinding;
import ua.meugen.android.pdfcomposer.model.utils.BitmapUtils;
import ua.meugen.android.pdfcomposer.ui.activities.base.BaseActivityModule;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragment;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.presenter.CreateNewPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.state.CreateNewState;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.view.CreateNewView;


public class CreateNewFragment extends BaseFragment<CreateNewState, CreateNewPresenter>
        implements CreateNewView {

    @Inject @ActivityContext Context context;

    private FragmentCreateNewBinding binding;

    @Nullable
    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        binding = FragmentCreateNewBinding.inflate(
                inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            final View view,
            @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setView(this);
        binding.text.addTextChangedListener(new TextWatcherImpl());
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
    public void exportToPdf() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
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
        if (imageUri == null) {
            binding.image.setImageResource(R.drawable.ic_photo_size_select_actual_black_48dp);
        } else {
            final int width = context.getResources().getInteger(R.integer.image_width);
            final int height = context.getResources().getInteger(R.integer.image_height);
            binding.image.setImageBitmap(BitmapUtils.tryDecodeBitmap(context,
                    imageUri, width, height));
        }
        binding.text.setText(text);
    }

    @Override
    public void pageDidntComplete() {
        Snackbar.make(binding.container,
                R.string.message_page_didnt_complete,
                Snackbar.LENGTH_LONG).show();
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
            presenter.onTextChanged(s.toString());
        }
    }
}
