package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.io.File

import ua.meugen.android.pdfcomposer.R
import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragment
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.adapters.OnPdfItemClickListener
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.binding.ViewRecentBinding
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.presenter.ViewRecentPresenter
import ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.view.ViewRecentView


class ViewRecentFragment : BaseFragment<MvpState, ViewRecentPresenter, ViewRecentBinding>(), ViewRecentView, OnPdfItemClickListener {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_recent,
                container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.setupRecycler()
        binding.displayNoItems()
    }

    override fun onStart() {
        super.onStart()
        presenter.load()
    }

    override fun displayItems(items: List<PdfItemEntity>) {
        binding.displayItems(items)
    }

    override fun onPdfItemClick(entity: PdfItemEntity) {
        val context = context

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.fromFile(File(entity.path!!))
        context!!.startActivity(Intent.createChooser(intent,
                getText(R.string.title_open_file_for_view)))
    }

    override fun createInjector(): Injector {
        return ViewRecentFragmentInjector(this)
    }
}
