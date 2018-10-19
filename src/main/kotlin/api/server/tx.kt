import io.reactivex.Observable

fun Server.getTxInfo(p: GetTxInfoParams): Observable<Transaction> {
    return ws.sendAndListen<GetTxInfoParams, GetTxInfoResponse>(p)
        .map { it.result }
}

fun Server.submitTx(p: SubmitTxParams): Observable<SubmitTxResult> {
    return ws.sendAndListen<SubmitTxParams, SubmitTxResponse>(p)
        .map { it.result }
}

fun Server.submitMultiSignedTx(p: SubmitMultiSignedTxParams): Observable<SubmitTxResult> {
    return ws.sendAndListen<SubmitMultiSignedTxParams, SubmitMultiSignedTxResponse>(p)
        .map { it.result }
}

fun Server.signTx(p: SignTxParams): Observable<SignTxResult> {
    return ws.sendAndListen<SignTxParams, SignTxResponse>(p)
        .map { it.result }
}

fun Server.signTxFor(p: SignTxForParams): Observable<SignTxResult> {
    return ws.sendAndListen<SignTxForParams, SignTxForResponse>(p)
        .map { it.result }
}