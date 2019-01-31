package ua.meugen.android.pdfcomposer.model.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

import java.io.IOException
import java.io.InputStream

object BitmapUtils {

    @Throws(IOException::class)
    fun uriToStream(
            context: Context,
            uri: String): InputStream? {
        return context
                .contentResolver
                .openInputStream(Uri.parse(uri))
    }

    @Throws(IOException::class)
    fun decodeBitmap(
            context: Context,
            uri: String,
            reqWidth: Int,
            reqHeight: Int): Bitmap? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        uriToStream(context, uri)!!.use { stream -> BitmapFactory.decodeStream(stream, null, options) }

        options.inSampleSize = 1
        if (options.outWidth > reqWidth || options.outHeight > reqHeight) {
            val halfWidth = options.outWidth / 2
            val halfHeight = options.outHeight / 2
            while (halfWidth / options.inSampleSize > reqWidth || halfHeight / options.inSampleSize > reqHeight) {
                options.inSampleSize *= 2
            }
        }
        options.inJustDecodeBounds = false
        uriToStream(context, uri)!!.use { stream -> return BitmapFactory.decodeStream(stream, null, options) }
    }
}
