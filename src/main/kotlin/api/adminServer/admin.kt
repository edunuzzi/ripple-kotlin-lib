import io.reactivex.Observable

fun AdminServer.generateKeypair(passphrase: String? = null): Observable<GenerateKeypairResult> {
    return ws.sendAndListen<GenerateKeypairParams, GenerateKeypairResponse>(GenerateKeypairParams(passphrase))
        .map { it.result }
}