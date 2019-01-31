package ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ua.meugen.android.pdfcomposer.R
import ua.meugen.android.pdfcomposer.model.data.PageContent
import ua.meugen.android.pdfcomposer.model.events.PdfExportProgressEvent
import ua.meugen.android.pdfcomposer.model.utils.toArrayList
import ua.meugen.android.pdfcomposer.ui.activities.base.Injector
import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.BaseFragment
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.binding.ProgressBinding
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.presenter.ProgressPresenter
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.state.ProgressState
import ua.meugen.android.pdfcomposer.ui.activities.createnew.fragments.progress.view.ProgressView


class ProgressFragment : BaseFragment<ProgressState, ProgressPresenter, ProgressBinding>(), ProgressView {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_progress,
                container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter!!.start()
    }

    override fun onUpdateProgress(event: PdfExportProgressEvent) {
        binding!!.displayProgress(event)
    }

    override fun createInjector(): Injector {
        return ProgressFragmentInjector(this)
    }

    companion object {

        fun build(name: String, pages: List<PageContent>): ProgressFragment {
            val args = Bundle()
            args.putString(ProgressState.PARAM_NAME, name)
            args.putParcelableArrayList(ProgressState.PARAM_PAGES,
                    pages.toArrayList)

            val fragment = ProgressFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
