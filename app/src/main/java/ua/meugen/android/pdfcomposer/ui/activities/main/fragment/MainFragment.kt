package ua.meugen.android.pdfcomposer.ui.activities.main.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ua.meugen.android.pdfcomposer.R
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragment
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter.MvpPresenter
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState
import ua.meugen.android.pdfcomposer.ui.activities.createnew.CreateNewActivity
import ua.meugen.android.pdfcomposer.ui.activities.main.fragment.binding.MainBinding
import ua.meugen.android.pdfcomposer.ui.activities.main.fragment.view.MainView
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.ViewRecentActivity

class MainFragment : BaseFragment<MvpState, MvpPresenter<MvpState>, MainBinding>(), MainView {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main,
                container, false)
    }

    override fun onViewCreated(
            view: View,
            savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setupListeners(this)
    }

    override fun createNewDocument() {
        val context = context
        CreateNewActivity.start(context!!)
    }

    override fun viewRecentDocuments() {
        val context = context
        ViewRecentActivity.start(context!!)
    }

    override fun createInjector(): Injector {
        return MainFragmentInjector(this)
    }
}
