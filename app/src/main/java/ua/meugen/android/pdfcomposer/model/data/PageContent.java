package ua.meugen.android.pdfcomposer.model.data;

import android.os.Parcel;
import android.os.Parcelable;

public class PageContent implements Parcelable {

    public static final Creator<PageContent> CREATOR = new Creator<PageContent>() {

        @Override
        public PageContent createFromParcel(final Parcel source) {
            return new PageContent(source);
        }

        @Override
        public PageContent[] newArray(final int size) {
            return new PageContent[size];
        }
    };

    private String imageUri;
    private String text;

    public PageContent() {}

    private PageContent(
            final Parcel source) {
        imageUri = source.readString();
        text = source.readString();
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(final String imageUri) {
        this.imageUri = imageUri;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(imageUri);
        dest.writeString(text);
    }
}
