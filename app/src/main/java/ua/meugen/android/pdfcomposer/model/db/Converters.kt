package ua.meugen.android.pdfcomposer.model.db

import android.arch.persistence.room.TypeConverter

import java.util.Date

object Converters {

    @JvmStatic
    @TypeConverter
    fun dateToMillis(date: Date): Long {
        return date.time
    }

    @JvmStatic
    @TypeConverter
    fun millisToDate(millis: Long): Date {
        return Date(millis)
    }
}
