package ua.meugen.android.pdfcomposer.model.utils;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionUtils {

    private CollectionUtils() {}

    public static <T> ArrayList<T> toArrayList(final Collection<T> collection) {
        return collection instanceof ArrayList
                ? (ArrayList<T>) collection
                : new ArrayList<>(collection);
    }
}
