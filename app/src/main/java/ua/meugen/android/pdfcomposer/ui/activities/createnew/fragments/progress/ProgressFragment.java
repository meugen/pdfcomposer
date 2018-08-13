package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ua.meugen.android.pdfcomposer.databinding.FragmentProgressBinding;
import ua.meugen.android.pdfcomposer.model.data.PageContent;
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent;
import ua.meugen.android.pdfcomposer.model.utils.CollectionUtils;
import ua.meugen.android.pdfcomposer.ui.activities.base.BaseActivityModule;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragment;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.presenter.ProgressPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state.ProgressState;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.view.ProgressView;


public class ProgressFragment extends BaseFragment<ProgressState, ProgressPresenter>
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

    @Inject @Named(BaseActivityModule.ACTIVITY_CONTEXT)
    Context context;

    private FragmentProgressBinding binding;

    @Nullable
    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        binding = FragmentProgressBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void onUpdateProgress(final PdfExportProgressEvent event) {
        binding.setEvent(event);
    }
}
