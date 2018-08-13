package ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding;

import android.support.v4.util.SparseArrayCompat;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * @author meugen
 */
public abstract class BaseBinding {

    private WeakReference<View> rootViewRef;
    private SparseArrayCompat<WeakReference<View>> childrenViewRefs;

    public void attachView(final View view) {
        rootViewRef = new WeakReference<>(view);
        childrenViewRefs = new SparseArrayCompat<>();
    }

    public void detachView() {
        rootViewRef = null;
        if (childrenViewRefs != null) {
            childrenViewRefs.clear();
        }
        childrenViewRefs = null;
    }

    private <V extends View> V getNullable(final int id) {
        if (rootViewRef == null || childrenViewRefs == null) {
            return null;
        }
        final View rootView = rootViewRef.get();
        if (rootView == null) {
            return null;
        }
        final WeakReference<View> childViewRef = childrenViewRefs.get(id);
        View childView = childViewRef == null ? null : childViewRef.get();
        if (childView == null) {
            childView = rootView.findViewById(id);
            if (childView != null) {
                childrenViewRefs.put(id, new WeakReference<>(childView));
            }
        }
        return (V) childView;
    }

    public <V extends View> V get(final int id) {
        final V view = getNullable(id);
        if (view == null) {
            throw new IllegalArgumentException("View with id " + id + " not found");
        }
        return view;
    }

    public boolean has(final int id) {
        return getNullable(id) != null;
    }
}
