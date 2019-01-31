package ua.meugen.android.pdfcomposer.app.di

import android.support.annotation.MainThread

import ua.meugen.android.pdfcomposer.model.actions.AppActionApi
import ua.meugen.android.pdfcomposer.model.actions.PdfExportRequest
import ua.meugen.android.pdfcomposer.model.db.dao.PdfItemDao
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent

interface AppComponent {

    @get:MainThread
    val pdfItemDao: PdfItemDao

    @get:MainThread
    val pdfExportActionApi: AppActionApi<PdfExportRequest, PdfExportProgressEvent>
}
