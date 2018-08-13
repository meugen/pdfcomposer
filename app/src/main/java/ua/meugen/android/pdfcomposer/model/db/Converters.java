package ua.meugen.android.pdfcomposer.model.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class Converters {

    @TypeConverter
    public static long dateToMillis(final Date date) {
        return date.getTime();
    }

    @TypeConverter
    public static Date millisToDate(final long millis) {
        return new Date(millis);
    }
}
