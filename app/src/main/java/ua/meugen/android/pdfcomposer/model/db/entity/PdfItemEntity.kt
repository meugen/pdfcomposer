package ua.meugen.android.pdfcomposer.model.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import java.util.Date

@Entity(tableName = "pdfitems")
class PdfItemEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String? = null
    var path: String? = null
    var size: Long = 0
    var date: Date? = null
}
