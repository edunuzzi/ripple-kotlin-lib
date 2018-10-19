import io.reactivex.Observable

fun Server.getAccountInfo(p: GetAccountParams): Observable<GetAccountResult> {
    return ws.sendAndListen<GetAccountParams, GetAccountResponse>(p)
        .map { it.result }
}

fun Server.getAccountTrustLines(p: GetTrustLinesParams): Observable<GetTrustLinesResult> {
    return ws.sendAndListen<GetTrustLinesParams, GetTrustLinesResponse>(p)
        .map { it.result }
}