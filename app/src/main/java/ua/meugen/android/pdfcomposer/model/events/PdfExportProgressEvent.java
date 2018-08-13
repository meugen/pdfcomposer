package ua.meugen.android.pdfcomposer.model.events;

public class PdfExportProgressEvent {

    public final int completedPages;
    public final int totalPages;

    public PdfExportProgressEvent(
            final int completedPages,
            final int totalPages) {
        this.completedPages = completedPages;
        this.totalPages = totalPages;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final PdfExportProgressEvent that = (PdfExportProgressEvent) o;

        if (completedPages != that.completedPages) return false;
        return totalPages == that.totalPages;
    }

    @Override
    public int hashCode() {
        int result = completedPages;
        result = 31 * result + totalPages;
        return result;
    }
}
