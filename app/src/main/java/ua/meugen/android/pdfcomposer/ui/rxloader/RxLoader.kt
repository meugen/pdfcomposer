package ua.meugen.android.pdfcomposer.ui.rxloader

import android.content.Context
import android.support.v4.content.Loader

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer


class RxLoader<T>(context: Context, private val observable: Observable<T>) : Loader<T>(context) {
    private var disposable: Disposable? = null
    private var emitter: ObservableEmitter<T>? = null
    private var data: T? = null
    private var error: Throwable? = null
    private var completed = false

    override fun onStartLoading() {
        super.onStartLoading()
        subscribe()
    }

    override fun onStopLoading() {
        super.onStopLoading()
        unsubscribe()
    }

    override fun onReset() {
        super.onReset()
        clear()
    }

    fun createObservable(): Observable<T> {
        return Observable.create<T> { e ->
            emitter = e
            if (error != null) {
                e.onError(error!!)
            } else if (data != null) {
                e.onNext(data!!)
            }
            if (completed) {
                e.onComplete()
            }
        }.doOnDispose(Action { this.unsubscribe() })
    }

    private fun subscribe() {
        if (disposable == null) {
            disposable = observable
                    .doOnDispose(Action { this.clear() })
                    .subscribe(Consumer<T> { this.onDataSuccess(it) }, Consumer<Throwable> { this.onDataError(it) }, Action { this.onDataCompleted() })
        }
    }

    private fun unsubscribe() {
        emitter = null
    }

    private fun onDataSuccess(data: T) {
        this.data = data
        if (emitter != null && !emitter!!.isDisposed && error == null) {
            emitter!!.onNext(data)
        }
    }

    private fun onDataError(th: Throwable) {
        this.error = th
        if (emitter != null && !emitter!!.isDisposed) {
            emitter!!.onError(th)
        }
    }

    private fun onDataCompleted() {
        this.completed = true
        if (emitter != null && !emitter!!.isDisposed) {
            emitter!!.onComplete()
        }
    }

    private fun clear() {
        if (disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }
        disposable = null
        emitter = null
        data = null
        error = null
    }
}
