package ua.meugen.android.pdfcomposer.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

import ua.meugen.android.pdfcomposer.model.db.dao.PdfItemDao
import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity

@Database(entities = [PdfItemEntity::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pdfItemDao(): PdfItemDao
}
