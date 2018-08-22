package ua.meugen.android.pdfcomposer.ui.activities.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.meugen.android.pdfcomposer.R;
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragment;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.MvpPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.CreateNewActivity;
import ua.meugen.android.pdfcomposer.ui.activities.main.fragment.binding.MainBinding;
import ua.meugen.android.pdfcomposer.ui.activities.main.fragment.view.MainView;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.ViewRecentActivity;

public class MainFragment extends BaseFragment<MvpState, MvpPresenter<MvpState>, MainBinding>
        implements MainView {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,
                container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull final View view,
            @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setupListeners(this);
    }

    @Override
    public void createNewDocument() {
        final Context context = getContext();
        CreateNewActivity.start(context);
    }

    @Override
    public void viewRecentDocuments() {
        final Context context = getContext();
        ViewRecentActivity.start(context);
    }

    @Override
    protected Injector createInjector() {
        return new MainFragmentInjector(this);
    }
}
