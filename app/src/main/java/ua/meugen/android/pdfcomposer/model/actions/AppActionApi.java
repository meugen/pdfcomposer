package ua.meugen.android.pdfcomposer.model.actions;

import io.reactivex.Observable;

public interface AppActionApi<Req, Resp> {

    Observable<Resp> action(Req req);
}
