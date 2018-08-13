package ua.meugen.android.pdfcomposer.model.actions;

import java.util.List;

import ua.meugen.android.pdfcomposer.model.data.PageContent;

/**
 * Created by meugen on 22.12.2017.
 */

public class PdfExportRequest {

    public final String name;
    public final List<PageContent> pages;

    public PdfExportRequest(
            final String name,
            final List<PageContent> pages) {
        this.name = name;
        this.pages = pages;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final PdfExportRequest that = (PdfExportRequest) o;

        if (!name.equals(that.name)) return false;
        return pages.equals(that.pages);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + pages.hashCode();
        return result;
    }
}
