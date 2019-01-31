package ua.meugen.android.pdfcomposer.model.actions

import io.reactivex.Observable

interface AppActionApi<Req, Resp> {

    fun action(req: Req): Observable<Resp>
}
