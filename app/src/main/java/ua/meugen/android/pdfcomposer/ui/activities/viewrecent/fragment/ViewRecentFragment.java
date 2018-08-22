package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.List;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity;
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragment;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.adapters.OnPdfItemClickListener;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.binding.ViewRecentBinding;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.presenter.ViewRecentPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.view.ViewRecentView;


public class ViewRecentFragment extends BaseFragment<MvpState, ViewRecentPresenter, ViewRecentBinding>
        implements ViewRecentView, OnPdfItemClickListener {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_recent,
                container, false);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setupRecycler();
        binding.displayNoItems();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
    }

    @Override
    public void displayItems(final List<PdfItemEntity> items) {
        binding.displayItems(items);
    }

    @Override
    public void onPdfItemClick(final PdfItemEntity entity) {
        final Context context = getContext();

        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.fromFile(new File(entity.path)));
        context.startActivity(Intent.createChooser(intent,
                getText(R.string.title_open_file_for_view)));
    }

    @Override
    protected Injector createInjector() {
        return new ViewRecentFragmentInjector(this);
    }
}
