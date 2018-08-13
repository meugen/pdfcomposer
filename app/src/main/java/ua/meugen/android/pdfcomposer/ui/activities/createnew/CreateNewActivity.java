package ua.meugen.android.pdfcomposer.ui.activities.createnew;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.model.PdfExporter;
import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.ui.activities.base.BaseActivity;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.AskForNameDialog;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.AskForNameListener;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.CreateNewFragment;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.ProgressFragment;
import ua.meugen.android.pdfcomposer.ui.activities.main.MainActivity;


public class CreateNewActivity extends BaseActivity implements PdfExporter, AskForNameListener {

    private static final String TAG = CreateNewActivity.class.getSimpleName();

    private static final String TAG_CREATE_NEW = "createNew";
    private static final String TAG_PROGRESS = "progress";

    public static void start(final Context context) {
        final Intent intent = new Intent(context, CreateNewActivity.class);
        context.startActivity(intent);
    }

    @Inject FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        if (savedInstanceState == null) {
            final CreateNewFragment fragment = new CreateNewFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.container, fragment, TAG_CREATE_NEW)
                    .commit();
        }
    }

    @Override
    public void onDocumentNamed(final String name, final List<PageContent> pages) {
        final Fragment createNewFragment
                = fragmentManager.findFragmentByTag(TAG_CREATE_NEW);
        fragmentManager.beginTransaction()
                .hide(createNewFragment)
                .add(R.id.container, ProgressFragment.build(name, pages), TAG_PROGRESS)
                .commit();
    }

    @Override
    public void exportPages(final List<PageContent> pages) {
        AskForNameDialog.build(pages).show(fragmentManager, "ask-for-name");
    }

    @Override
    public void onPdfExportSuccess() {
        MainActivity.start(this, true);
    }

    @Override
    public void onPdfExportError(final Throwable th) {
        Log.d(TAG, th.getMessage(), th);

        final Fragment createNewFragment
                = fragmentManager.findFragmentByTag(TAG_CREATE_NEW);
        final Fragment progressFragment
                = fragmentManager.findFragmentByTag(TAG_PROGRESS);
        fragmentManager.beginTransaction()
                .remove(progressFragment)
                .show(createNewFragment)
                .commit();
    }
}
