package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.binding;

import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding.Binding;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.view.CreateNewView;

/**
 * @author meugen
 */
public interface CreateNewBinding extends Binding {

    void setupListeners(CreateNewView view);

    void displayContent(String imageUri, String text);
}
