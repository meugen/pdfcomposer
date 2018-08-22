package ua.meugen.android.pdfcomposer.ui.rxloader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import ua.meugen.android.pdfcomposer.model.utils.RxUtils;

public class LoaderLifecycleHandler implements LifecycleHandler {

    private final Context context;
    private final LoaderManager manager;

    public LoaderLifecycleHandler(
            final Context context,
            final LoaderManager manager) {
        this.context = context;
        this.manager = manager;
    }

    private <T> ObservableSource<T> load(
            final Observable<T> upstream,
            final int id) {
        final Observable<T> ob = upstream.compose(RxUtils.async());
        manager.initLoader(id, Bundle.EMPTY,
                new RxLoaderCallbacks<>(context, ob));
        RxLoader<T> loader = (RxLoader<T>) manager.getLoader(id);
        return loader.createObservable();
    }

    @Override
    public <T> ObservableTransformer<T, T> load(final int id) {
        return upstream -> load(upstream, id);
    }

    @Override
    public void clear(final int id) {
        manager.destroyLoader(id);
    }

    private static class RxLoaderCallbacks<T> implements LoaderManager.LoaderCallbacks<T> {

        private final Context context;
        private final Observable<T> observable;

        RxLoaderCallbacks(final Context context, final Observable<T> observable) {
            this.context = context;
            this.observable = observable;
        }

        @Override
        public Loader<T> onCreateLoader(final int id, final Bundle args) {
            return new RxLoader<>(context, observable);
        }

        @Override
        public void onLoadFinished(final Loader<T> loader, final T data) {}

        @Override
        public void onLoaderReset(final Loader<T> loader) {}
    }
}
