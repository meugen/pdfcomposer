package ua.meugen.android.pdfcomposer.model.utils

import java.util.ArrayList

val <T> Collection<T>.toArrayList: ArrayList<T>
    get() = if (this is ArrayList<*>) this as ArrayList<T> else ArrayList(this)
