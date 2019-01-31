package ua.meugen.android.pdfcomposer.model.actions

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.print.PrintAttributes
import android.print.pdf.PrintedPdfDocument
import android.util.Log

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.Date

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import ua.meugen.android.pdfcomposer.R
import ua.meugen.android.pdfcomposer.model.data.PageContent
import ua.meugen.android.pdfcomposer.model.db.dao.PdfItemDao
import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent
import ua.meugen.android.pdfcomposer.model.utils.BitmapUtils

class PdfExportActionApi(
        private val context: Context,
        private val pdfItemDao: PdfItemDao) : AppActionApi<PdfExportRequest, PdfExportProgressEvent> {

    private var paint: Paint? = null

    override fun action(request: PdfExportRequest): Observable<PdfExportProgressEvent> {
        return Observable.create { e -> exportPdf(e, request) }
    }

    private fun exportPdf(
            emitter: ObservableEmitter<PdfExportProgressEvent>,
            request: PdfExportRequest) {
        val attrs = PrintAttributes.Builder()
                .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
                .setColorMode(PrintAttributes.COLOR_MODE_COLOR)
                .build()
        val doc = PrintedPdfDocument(context, attrs)

        this.paint = Paint()
        try {
            sendEvent(emitter, PdfExportProgressEvent(
                    0, request.pages.size))
            for (i in request.pages.indices) {
                val page = doc.startPage(i)
                drawPage(page, request.pages[i])
                doc.finishPage(page)

                sendEvent(emitter, PdfExportProgressEvent(
                        i + 1, request.pages.size))
            }

            val dir = Environment.getExternalStoragePublicDirectory("pdf")
            dir.mkdirs()
            var file = File(dir, request.name + ".pdf")
            if (file.exists()) {
                var index = 1
                while (true) {
                    file = File(dir, request.name + "-" + index + ".pdf")
                    if (!file.exists()) {
                        break
                    }
                    index++
                }
            }
            try {
                FileOutputStream(file).use { outputStream -> doc.writeTo(outputStream) }
            } catch (e: IOException) {
                Log.d(TAG, e.message, e)
            }

            val item = PdfItemEntity()
            item.date = Date(System.currentTimeMillis())
            item.name = file.name
            item.path = file.path
            item.size = file.length()
            pdfItemDao.insert(item)
            if (!emitter.isDisposed) {
                emitter.onComplete()
            }
        } catch (th: Throwable) {
            Log.d(TAG, th.message, th)
        } finally {
            this.paint = null
            doc.close()
        }
    }

    private fun sendEvent(
            emitter: ObservableEmitter<PdfExportProgressEvent>,
            event: PdfExportProgressEvent) {
        if (!emitter.isDisposed) {
            emitter.onNext(event)
        }
    }

    private fun drawPage(
            page: PdfDocument.Page,
            content: PageContent) {
        var bitmap: Bitmap? = null
        try {
            val resources = context.resources
            val margin = resources.getDimensionPixelSize(R.dimen.pdf_margin)
            val textSize = resources.getDimensionPixelSize(R.dimen.pdf_text_size)

            var contentRect = Rect(page.info.contentRect)
            contentRect.left += margin
            contentRect.right -= margin
            contentRect.bottom = contentRect.top + contentRect.height() * 3 / 4
            contentRect.top += margin
            bitmap = BitmapUtils.decodeBitmap(context,
                    content.imageUri!!, contentRect.width(),
                    contentRect.height())
            var bitmapWidth = bitmap!!.width.toFloat()
            var bitmapHeight = bitmap.height.toFloat()
            if (bitmapWidth > contentRect.width()) {
                val ratio = contentRect.width() / bitmapWidth
                bitmapWidth *= ratio
                bitmapHeight *= ratio
            }
            if (bitmapHeight > contentRect.height()) {
                val ratio = contentRect.height() / bitmapHeight
                bitmapWidth *= ratio
                bitmapHeight *= ratio
            }
            contentRect.left = margin + (contentRect.width() - bitmapWidth.toInt()) / 2
            contentRect.right = contentRect.left + bitmapWidth.toInt()
            contentRect.top = margin + (contentRect.height() - bitmapHeight.toInt()) / 2
            contentRect.bottom = contentRect.top + bitmapHeight.toInt()
            page.canvas.drawBitmap(bitmap,
                    Rect(0, 0, bitmap.width, bitmap.height),
                    contentRect, paint)

            paint!!.color = Color.BLACK
            paint!!.textAlign = Paint.Align.CENTER
            paint!!.textSize = textSize.toFloat()
            contentRect = Rect(page.info.contentRect)
            contentRect.top = contentRect.top + contentRect.height() * 3 / 4
            page.canvas.drawText(content.text,
                    contentRect.exactCenterX(),
                    contentRect.exactCenterY(), paint!!)
        } catch (e: IOException) {
            Log.d(TAG, e.message, e)
        } finally {
            bitmap?.recycle()
        }
    }

    companion object {

        private val TAG = PdfExportActionApi::class.java.simpleName
    }
}
