import io.reactivex.Observable

fun Server.getTxInfo(p: GetTxInfoParams): Observable<GetTxInfoResult> {
    return ws.sendAndListen<GetTxInfoParams, GetTxInfoResponse>(p)
        .map { it.result }
}

fun Server.submitTx(p: SubmitTxParams): Observable<SubmitTxResult> {
    return ws.sendAndListen<SubmitTxParams, SubmitTxResponse>(p)
        .map { it.result }
}