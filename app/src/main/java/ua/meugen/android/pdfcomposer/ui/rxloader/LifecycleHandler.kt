package ua.meugen.android.pdfcomposer.ui.rxloader

import io.reactivex.ObservableTransformer

interface LifecycleHandler {

    fun <T> load(id: Int): ObservableTransformer<T, T>

    fun clear(id: Int)
}
