package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent;
import ua.meugen.android.pdfcomposer.model.utils.CollectionUtils;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragment;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.binding.ProgressBinding;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.presenter.ProgressPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state.ProgressState;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.view.ProgressView;


public class ProgressFragment extends BaseFragment<ProgressState, ProgressPresenter, ProgressBinding>
        implements ProgressView {

    public static ProgressFragment build(final String name, final List<PageContent> pages) {
        final Bundle args = new Bundle();
        args.putString(ProgressState.PARAM_NAME, name);
        args.putParcelableArrayList(ProgressState.PARAM_PAGES,
                CollectionUtils.toArrayList(pages));

        final ProgressFragment fragment = new ProgressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_progress,
                container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void onUpdateProgress(final PdfExportProgressEvent event) {
        binding.displayProgress(event);
    }
}
