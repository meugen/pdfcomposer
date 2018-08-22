package ua.meugen.android.pdfcomposer.app;

import android.app.Application;
import android.content.Context;

import ua.meugen.android.pdfcomposer.app.di.AppComponent;
import ua.meugen.android.pdfcomposer.app.di.AppComponentImpl;

public class PdfComposer extends Application {

    public static PdfComposer from(final Context context) {
        return (PdfComposer) context.getApplicationContext();
    }

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = new AppComponentImpl(this);
    }

    public AppComponent getAppComponent() {
        return component;
    }
}
