package ua.meugen.android.pdfcomposer.ui.activities.createnew;

import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ua.meugen.android.pdfcomposer.model.PdfExporter;
import ua.meugen.android.pdfcomposer.ui.activities.base.BaseActivityModule;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.AskForNameDialog;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.AskForNameDialogModule;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.dialogs.askforname.AskForNameListener;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.CreateNewFragment;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.createnew.CreateNewFragmentModule;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.ProgressFragment;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.ProgressFragmentModule;

@Module(includes = BaseActivityModule.class)
public abstract class CreateNewActivityModule {

    @Binds
    abstract AppCompatActivity bindAppCompatActivity(final CreateNewActivity activity);

    @Binds
    abstract PdfExporter bindPdfExporter(final CreateNewActivity activity);

    @Binds
    abstract AskForNameListener bindAskForNameListener(final CreateNewActivity activity);

    @ContributesAndroidInjector(modules = CreateNewFragmentModule.class)
    abstract CreateNewFragment contributeCreateNewFragment();

    @ContributesAndroidInjector(modules = ProgressFragmentModule.class)
    abstract ProgressFragment contributeProgressFragment();

    @ContributesAndroidInjector(modules = AskForNameDialogModule.class)
    abstract AskForNameDialog contributeAskForNameDialog();
}
