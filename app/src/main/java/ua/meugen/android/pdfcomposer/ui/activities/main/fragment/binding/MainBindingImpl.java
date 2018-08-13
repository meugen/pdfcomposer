package ua.meugen.android.pdfcomposer.ui.activities.main.fragment.binding;

import android.view.View;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding.BaseBinding;
import ua.meugen.android.pdfcomposer.ui.activities.main.fragment.view.MainView;

/**
 * @author meugen
 */
public class MainBindingImpl extends BaseBinding
        implements MainBinding {

    @Inject
    MainBindingImpl() {}

    @Override
    public void setupListeners(final MainView view) {
        final OnClickListenerImpl impl = new OnClickListenerImpl(view);
        get(R.id.create_new_document).setOnClickListener(impl);
        get(R.id.view_recent_documents).setOnClickListener(impl);
    }

    private static class OnClickListenerImpl implements View.OnClickListener {

        private final WeakReference<MainView> viewRef;

        OnClickListenerImpl(final MainView view) {
            viewRef = new WeakReference<>(view);
        }

        @Override
        public void onClick(final View v) {
            final MainView view = viewRef.get();
            if (view == null) {
                return;
            }
            final int id = v.getId();
            if (id == R.id.create_new_document) {
                view.createNewDocument();
            } else if (id == R.id.view_recent_documents) {
                view.viewRecentDocuments();
            }
        }
    }
}
