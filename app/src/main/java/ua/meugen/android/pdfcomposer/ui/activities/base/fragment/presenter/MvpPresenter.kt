package ua.meugen.android.pdfcomposer.ui.activities.base.fragment.presenter

import ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state.MvpState

/**
 * Created by meugen on 21.12.2017.
 */

interface MvpPresenter<S : MvpState> {

    fun onSaveState(state: S)

    fun onRestoreState(state: S)

    fun clear()
}
