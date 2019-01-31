package ua.meugen.android.pdfcomposer.app.di

import java.lang.ref.SoftReference

/**
 * @author meugen
 */
abstract class Singleton<T> {

    abstract val singleton: T?

    abstract fun putSingleton(inst: T)

    operator fun get(creator: Creator<T>): T {
        singleton?.let { return it }
        val instance = creator.create()
        putSingleton(instance)
        return instance
    }

    private class SoftSingleton<T> : Singleton<T>() {

        private var ref: SoftReference<T>? = null

        override val singleton: T?
            get() = ref?.get()

        override fun putSingleton(inst: T) {
            ref = SoftReference<T>(inst)
        }
    }

    private class StrongSingleton<T> : Singleton<T>() {

        override var singleton: T? = null
            private set

        override fun putSingleton(inst: T) {
            singleton = inst
        }
    }

    interface Creator<T> {

        fun create(): T
    }

    companion object {

        fun <T> create(soft: Boolean): Singleton<T>
                = if (soft) SoftSingleton() else StrongSingleton()
    }
}
