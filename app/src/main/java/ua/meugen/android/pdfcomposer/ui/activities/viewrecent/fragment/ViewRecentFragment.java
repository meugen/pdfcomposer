package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.app.di.qualifiers.ActivityContext;
import ua.meugen.android.pdfcomposer.databinding.FragmentViewRecentBinding;
import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity;
import ua.meugen.android.pdfcomposer.ui.activities.base.BaseActivityModule;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragment;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.adapters.OnPdfItemClickListener;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.adapters.PdfItemsAdapter;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.presenter.ViewRecentPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.view.ViewRecentView;


public class ViewRecentFragment extends BaseFragment<MvpState, ViewRecentPresenter>
        implements ViewRecentView, OnPdfItemClickListener {

    @Inject @ActivityContext Context context;
    @Inject PdfItemsAdapter adapter;

    private FragmentViewRecentBinding binding;

    @Nullable
    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        binding = FragmentViewRecentBinding.inflate(
                inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            final View view,
            @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recycler.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL));
        binding.recycler.setAdapter(adapter);
        binding.setHasItems(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
    }

    @Override
    public void displayItems(final List<PdfItemEntity> items) {
        adapter.swapItems(items);
        binding.setHasItems(items.size() > 0);
    }

    @Override
    public void onPdfItemClick(final PdfItemEntity entity) {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.fromFile(new File(entity.path)));
        context.startActivity(Intent.createChooser(intent,
                getText(R.string.title_open_file_for_view)));
    }
}
