package ua.meugen.android.pdfcomposer.ui.rxloader

import android.content.Context
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import ua.meugen.android.pdfcomposer.model.utils.RxUtils

class LoaderLifecycleHandler(
        private val context: Context,
        private val manager: LoaderManager) : LifecycleHandler {

    private fun <T> load(
            upstream: Observable<T>,
            id: Int): ObservableSource<T> {
        val ob = upstream.compose(RxUtils.async())
        manager.initLoader(id, Bundle.EMPTY,
                RxLoaderCallbacks(context, ob))
        val loader = manager.getLoader<Any>(id) as RxLoader<T>?
        return loader!!.createObservable()
    }

    override fun <T> load(id: Int): ObservableTransformer<T, T> {
        return object: ObservableTransformer<T, T> {
            override fun apply(upstream: Observable<T>): ObservableSource<T> {
                return load(upstream, id)
            }
        }
    }

    override fun clear(id: Int) {
        manager.destroyLoader(id)
    }

    private class RxLoaderCallbacks<T> internal constructor(private val context: Context, private val observable: Observable<T>) : LoaderManager.LoaderCallbacks<T> {

        override fun onCreateLoader(id: Int, args: Bundle?): Loader<T> {
            return RxLoader(context, observable)
        }

        override fun onLoadFinished(loader: Loader<T>, data: T) {}

        override fun onLoaderReset(loader: Loader<T>) {}
    }
}
