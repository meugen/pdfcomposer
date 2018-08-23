package ua.meugen.android.pdfcomposer.app.di;

import java.lang.ref.SoftReference;

/**
 * @author meugen
 */
abstract class Singleton<T> {

    static <T> Singleton<T> create(final boolean soft) {
        return soft
                ? new SoftSingleton<>()
                : new StrongSingleton<>();
    }

    abstract T getSingleton();

    abstract void putSingleton(final T inst);

    T get(final Creator<T> creator) {
        T result = getSingleton();
        if (result == null) {
            result = creator.create();
            putSingleton(result);
        }
        return result;
    }

    private static class SoftSingleton<T> extends Singleton<T> {

        private SoftReference<T> ref;

        @Override
        T getSingleton() {
            return ref == null ? null : ref.get();
        }

        @Override
        void putSingleton(final T inst) {
            ref = new SoftReference<>(inst);
        }
    }

    private static class StrongSingleton<T> extends Singleton<T> {

        private T ref;

        @Override
        T getSingleton() {
            return ref;
        }

        @Override
        void putSingleton(final T inst) {
            ref = inst;
        }
    }

    interface Creator<T> {

        T create();
    }
}
