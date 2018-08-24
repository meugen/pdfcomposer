package ua.meugen.android.pdfcomposer.ui.activities.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.ui.activities.base.BaseActivity;
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.ViewRecentActivity;

public class MainActivity extends BaseActivity {

    private static final String EXTRA_OPEN_RECENT_PDFS = "openRecentPdfs";

    public static void start(final Context context, final boolean openRecentPdfs) {
        final Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_OPEN_RECENT_PDFS, openRecentPdfs);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        if (intent.getBooleanExtra(EXTRA_OPEN_RECENT_PDFS, false)) {
            ViewRecentActivity.start(this);
        }
    }

    @NonNull
    @Override
    protected Injector createInjector() {
        return new Injector.Empty();
    }
}
