package ua.meugen.android.pdfcomposer.model.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;

public class BitmapUtils {

    private BitmapUtils() {}

    public static InputStream uriToStream(
            final Context context,
            final String uri) throws IOException {
        return context
                .getContentResolver()
                .openInputStream(Uri.parse(uri));
    }

    public static Bitmap decodeBitmap(
            final Context context,
            final String uri,
            final int reqWidth,
            final int reqHeight) throws IOException {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try (InputStream stream = uriToStream(context, uri)) {
            BitmapFactory.decodeStream(stream, null, options);
        }

        options.inSampleSize = 1;
        if (options.outWidth > reqWidth || options.outHeight > reqHeight) {
            int halfWidth = options.outWidth / 2;
            int halfHeight = options.outHeight / 2;
            while (halfWidth / options.inSampleSize > reqWidth
                    || halfHeight / options.inSampleSize > reqHeight) {
                options.inSampleSize *= 2;
            }
        }
        options.inJustDecodeBounds = false;
        try (InputStream stream = uriToStream(context, uri)) {
            return BitmapFactory.decodeStream(stream, null, options);
        }
    }

    public static Bitmap tryDecodeBitmap(
            final Context context,
            final String uri,
            final int reqWidth,
            final int reqHeight) {
        try {
            return decodeBitmap(context, uri, reqWidth, reqHeight);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
