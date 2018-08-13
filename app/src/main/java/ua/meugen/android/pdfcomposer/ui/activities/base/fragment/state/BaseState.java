package ua.meugen.android.pdfcomposer.ui.activities.base.fragment.state;

import android.os.Bundle;

public class BaseState implements MvpState {

    protected Bundle bundle;

    public void attachBundle(final Bundle bundle) {
        this.bundle = bundle == null ? Bundle.EMPTY : bundle;
    }

    public void detachBundle() {
        this.bundle = null;
    }
}
