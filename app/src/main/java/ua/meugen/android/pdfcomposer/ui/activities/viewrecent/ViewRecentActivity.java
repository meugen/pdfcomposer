package ua.meugen.android.pdfcomposer.ui.activities.viewrecent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.ui.activities.base.BaseActivity;


public class ViewRecentActivity extends BaseActivity {

    public static void start(final Context context) {
        final Intent intent = new Intent(context, ViewRecentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recent);
    }
}
