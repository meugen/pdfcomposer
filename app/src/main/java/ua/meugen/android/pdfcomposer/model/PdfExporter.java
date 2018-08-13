package ua.meugen.android.pdfcomposer.model;

import java.util.List;

import ua.meugen.android.pdfcomposer.model.data.PageContent;

public interface PdfExporter {

    void exportPages(List<PageContent> pages);

    void onPdfExportSuccess();

    void onPdfExportError(Throwable th);
}
