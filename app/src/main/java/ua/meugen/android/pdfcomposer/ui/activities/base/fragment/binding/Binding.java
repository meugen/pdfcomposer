package ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding;

import android.view.View;

/**
 * @author meugen
 */
public interface Binding {

    void attachView(View view);

    void detachView();

    <V extends View> V get(int id);

    boolean has(int id);
}
