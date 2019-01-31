package ua.meugen.android.pdfcomposer.model.data

import android.os.Parcel
import android.os.Parcelable

class PageContent: Parcelable {

    var imageUri: String? = null
    var text: String? = null

    constructor()

    private constructor(
            source: Parcel) {
        imageUri = source.readString()
        text = source.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(imageUri)
        dest.writeString(text)
    }

    companion object CREATOR: Parcelable.Creator<PageContent> {

        override fun createFromParcel(source: Parcel): PageContent {
            return PageContent(source)
        }

        override fun newArray(size: Int): Array<PageContent?> = arrayOfNulls(size)
    }
}
