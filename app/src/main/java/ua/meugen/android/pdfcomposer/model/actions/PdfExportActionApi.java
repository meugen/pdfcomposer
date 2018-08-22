package ua.meugen.android.pdfcomposer.model.actions;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.model.db.dao.PdfItemDao;
import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity;
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent;
import ua.meugen.android.pdfcomposer.model.utils.BitmapUtils;

public class PdfExportActionApi implements AppActionApi<PdfExportRequest, PdfExportProgressEvent> {

    private static final String TAG = PdfExportActionApi.class.getSimpleName();

    private final Context context;
    private final PdfItemDao pdfItemDao;

    private Paint paint;

    public PdfExportActionApi(
            final Context context,
            final PdfItemDao pdfItemDao) {
        this.context = context;
        this.pdfItemDao = pdfItemDao;
    }

    @Override
    public Observable<PdfExportProgressEvent> action(final PdfExportRequest request) {
        return Observable.create(e -> exportPdf(e, request));
    }

    private void exportPdf(
            final ObservableEmitter<PdfExportProgressEvent> emitter,
            final PdfExportRequest request) {
        final PrintAttributes attrs = new PrintAttributes.Builder()
                .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
                .setColorMode(PrintAttributes.COLOR_MODE_COLOR)
                .build();
        final PrintedPdfDocument doc = new PrintedPdfDocument(context, attrs);

        this.paint = new Paint();
        try {
            sendEvent(emitter, new PdfExportProgressEvent(
                    0, request.pages.size()));
            for (int i = 0; i < request.pages.size(); i++) {
                final PdfDocument.Page page = doc.startPage(i);
                drawPage(page, request.pages.get(i));
                doc.finishPage(page);

                sendEvent(emitter, new PdfExportProgressEvent(
                        i + 1, request.pages.size()));
            }

            final File dir = Environment.getExternalStoragePublicDirectory("pdf");
            dir.mkdirs();
            File file = new File(dir, request.name + ".pdf");
            if (file.exists()) {
                int index = 1;
                while (true) {
                    file = new File(dir, request.name + "-" + index + ".pdf");
                    if (!file.exists()) {
                        break;
                    }
                    index++;
                }
            }
            try (OutputStream outputStream = new FileOutputStream(file)) {
                doc.writeTo(outputStream);
            } catch (IOException e) {
                Log.d(TAG, e.getMessage(), e);
            }

            final PdfItemEntity item = new PdfItemEntity();
            item.date = new Date(System.currentTimeMillis());
            item.name = file.getName();
            item.path = file.getPath();
            item.size = file.length();
            pdfItemDao.insert(item);
            if (!emitter.isDisposed()) {
                emitter.onComplete();
            }
        } catch (Throwable th) {
            Log.d(TAG, th.getMessage(), th);
        } finally {
            this.paint = null;
            doc.close();
        }
    }

    private void sendEvent(
            final ObservableEmitter<PdfExportProgressEvent> emitter,
            final PdfExportProgressEvent event) {
        if (!emitter.isDisposed()) {
            emitter.onNext(event);
        }
    }

    private void drawPage(
            final PdfDocument.Page page,
            final PageContent content) {
        Bitmap bitmap = null;
        try {
            final Resources resources = context.getResources();
            final int margin = resources.getDimensionPixelSize(R.dimen.pdf_margin);
            final int textSize = resources.getDimensionPixelSize(R.dimen.pdf_text_size);

            Rect contentRect = new Rect(page.getInfo().getContentRect());
            contentRect.left += margin;
            contentRect.right -= margin;
            contentRect.bottom = contentRect.top + contentRect.height() * 3 / 4;
            contentRect.top += margin;
            bitmap = BitmapUtils.decodeBitmap(context,
                    content.getImageUri(), contentRect.width(),
                    contentRect.height());
            float bitmapWidth = bitmap.getWidth(), bitmapHeight = bitmap.getHeight();
            if (bitmapWidth > contentRect.width()) {
                float ratio = contentRect.width() / bitmapWidth;
                bitmapWidth *= ratio;
                bitmapHeight *= ratio;
            }
            if (bitmapHeight > contentRect.height()) {
                float ratio = contentRect.height() / bitmapHeight;
                bitmapWidth *= ratio;
                bitmapHeight *= ratio;
            }
            contentRect.left = margin + (contentRect.width() - (int) bitmapWidth) / 2;
            contentRect.right = contentRect.left + (int) bitmapWidth;
            contentRect.top = margin + (contentRect.height() - (int) bitmapHeight) / 2;
            contentRect.bottom = contentRect.top + (int) bitmapHeight;
            page.getCanvas().drawBitmap(bitmap,
                    new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                    contentRect, paint);

            paint.setColor(Color.BLACK);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(textSize);
            contentRect = new Rect(page.getInfo().getContentRect());
            contentRect.top = contentRect.top + contentRect.height() * 3 / 4;
            page.getCanvas().drawText(content.getText(),
                    contentRect.exactCenterX(),
                    contentRect.exactCenterY(), paint);
        } catch (IOException e) {
            Log.d(TAG, e.getMessage(), e);
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
            }
        }
    }
}
