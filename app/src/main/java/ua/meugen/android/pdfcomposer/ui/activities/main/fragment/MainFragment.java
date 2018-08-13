package ua.meugen.android.pdfcomposer.ui.activities.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;
import javax.inject.Named;

import ua.meugen.android.pdfcomposer.databinding.FragmentMainBinding;
import ua.meugen.android.pdfcomposer.ui.activities.base.BaseActivityModule;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragment;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.MvpPresenter;
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState;
import ua.meugen.android.pdfcomposer.ui.activities.createnew.CreateNewActivity;
import ua.meugen.android.pdfcomposer.ui.activities.main.fragment.view.MainView;
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.ViewRecentActivity;

public class MainFragment extends BaseFragment<MvpState, MvpPresenter<MvpState>> implements MainView {

    @Inject @Named(BaseActivityModule.ACTIVITY_CONTEXT)
    Context context;

    private FragmentMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(
                inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            final View view,
            @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setView(this);
    }

    @Override
    public void createNewDocument() {
        CreateNewActivity.start(context);
    }

    @Override
    public void viewRecentDocuments() {
        ViewRecentActivity.start(context);
    }
}
