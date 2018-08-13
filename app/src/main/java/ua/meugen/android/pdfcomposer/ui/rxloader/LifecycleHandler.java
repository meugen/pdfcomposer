package ua.meugen.android.pdfcomposer.ui.rxloader;

import io.reactivex.ObservableTransformer;

public interface LifecycleHandler {

    <T> ObservableTransformer<T, T> load(int id);

    void clear(int id);
}
