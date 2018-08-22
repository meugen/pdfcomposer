package ua.meugen.android.pdfcomposer.ui.activities.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ua.meugen.android.pdfcomposer.app.PdfComposer;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        inject();
        super.onCreate(savedInstanceState);
    }

    private void inject() {
        final Injector injector = createInjector();
        injector.inject(PdfComposer.from(this).getAppComponent());
    }

    @NonNull
    protected abstract Injector createInjector();
}
