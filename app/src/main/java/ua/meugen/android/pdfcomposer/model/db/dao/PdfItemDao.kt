package ua.meugen.android.pdfcomposer.model.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import io.reactivex.Single
import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity

@Dao
interface PdfItemDao {

    @get:Query("SELECT * FROM pdfitems ORDER BY date DESC")
    val allPdfItems: Single<List<PdfItemEntity>>

    @Insert
    fun insert(vararg entity: PdfItemEntity)
}
