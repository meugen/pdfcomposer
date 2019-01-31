package ua.meugen.android.pdfcomposer.app.di

import android.arch.persistence.room.Room
import android.content.Context

import ua.meugen.android.pdfcomposer.app.PdfComposer
import ua.meugen.android.pdfcomposer.model.actions.AppActionApi
import ua.meugen.android.pdfcomposer.model.actions.PdfExportActionApi
import ua.meugen.android.pdfcomposer.model.actions.PdfExportRequest
import ua.meugen.android.pdfcomposer.model.db.AppDatabase
import ua.meugen.android.pdfcomposer.model.db.dao.PdfItemDao
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent

/**
 * @author meugen
 */
class AppComponentImpl(private val composer: PdfComposer) : AppComponent {

    private val pdfItemDaoSingleton = Singleton.create<PdfItemDao>(true)

    override val pdfExportActionApi: AppActionApi<PdfExportRequest, PdfExportProgressEvent>
        get() = PdfExportActionApi(composer, pdfItemDao)

    override val pdfItemDao: PdfItemDao
        get() = pdfItemDaoSingleton.get(PdfItemDaoCreator(composer))

    private class PdfItemDaoCreator(private val context: Context) : Singleton.Creator<PdfItemDao> {

        override fun create(): PdfItemDao {
            val db = createAppDatabase()
            return db.pdfItemDao()
        }

        private fun createAppDatabase(): AppDatabase {
            return Room
                    .databaseBuilder(context, AppDatabase::class.java, "pdfcomposer")
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}
