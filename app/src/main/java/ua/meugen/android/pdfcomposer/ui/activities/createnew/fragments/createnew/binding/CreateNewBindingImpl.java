package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.binding;

import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding.BaseBinding;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.view.CreateNewView;

/**
 * @author meugen
 */
public class CreateNewBindingImpl extends BaseBinding
        implements CreateNewBinding {

    @Inject
    CreateNewBindingImpl() {}

    @Override
    public void setupListeners(final CreateNewView view) {
        final View.OnClickListener listener
                = new OnClickListenerImpl(view);
        get(R.id.export_to_pdf).setOnClickListener(listener);
        get(R.id.load_image).setOnClickListener(listener);
        get(R.id.add_page).setOnClickListener(listener);

        final TextWatcher watcher = new TextWatcherImpl(view);
        this.<EditText>get(R.id.text).addTextChangedListener(watcher);
    }

    @Override
    public void displayContent(final String imageUri, final String text) {
        final ImageView imageView = get(R.id.image);
        if (imageUri == null) {
            imageView.setImageResource(R.drawable.ic_photo_size_select_actual_black_48dp);
        } else {
            imageView.setImageURI(Uri.parse(imageUri));
        }
        final EditText editText = get(R.id.text);
        editText.setText(text);
    }

    private static class OnClickListenerImpl implements View.OnClickListener {

        private final WeakReference<CreateNewView> viewRef;

        OnClickListenerImpl(final CreateNewView view) {
            this.viewRef = new WeakReference<>(view);
        }

        @Override
        public void onClick(final View v) {
            final CreateNewView view = viewRef.get();
            if (view == null) {
                return;
            }
            final int id = v.getId();
            if (id == R.id.export_to_pdf) {
                view.exportToPdf();
            } else if (id == R.id.load_image) {
                view.loadImage();
            } else if (id == R.id.add_page) {
                view.addPage();
            }
        }
    }

    private static class TextWatcherImpl implements TextWatcher {

        private final WeakReference<CreateNewView> viewRef;

        TextWatcherImpl(final CreateNewView view) {
            this.viewRef = new WeakReference<>(view);
        }

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
            final CreateNewView view = viewRef.get();
            if (view == null) {
                return;
            }
            view.textChanged(s);
        }
    }
}
