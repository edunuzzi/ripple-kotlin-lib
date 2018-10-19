import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class WebSocket : WebSocketClient {

    val res = BehaviorSubject.create<String>()
    val gson = Gson()

    val connectionErrorID = "-1"

    constructor(serverURI: URI) : super(serverURI)

    constructor(serverUri: URI, httpHeaders: Map<String, String>) : super(serverUri, httpHeaders)

    override fun onOpen(handshakedata: ServerHandshake) {
        println("opened connection")
    }

    override fun onMessage(message: String) {
        res.onNext(message)
    }

    override fun onClose(code: Int, reason: String, remote: Boolean) {
        println("Connection closed by " + (if (remote) "remote peer" else "us") + " Code: " + code + " Reason: " + reason)
    }

    override fun onError(ex: Exception) {
        ex.printStackTrace()
        res.onNext(
            gson.toJson(
                ErrorResponse(
                    Status.Error,
                    ErrorType.UnknownErr,
                    connectionErrorID,
                    -1,
                    ex.toString(),
                    null
                )
            )
        )
    }

    inline fun <T : Param, reified S : Response> sendAndListen(obj: T): Observable<S> {
        super.send(gson.toJson(obj))

        return res
            .map {
                val parsedjson = gson.fromJson(it, S::class.java)

                if (
                    parsedjson.status == Status.Error &&
                    (parsedjson.id == obj.id || parsedjson.id == connectionErrorID)
                ) {
                    throw gson.fromJson(it, ErrorResponse::class.java)
                }

                parsedjson
            }
            .filter { it.id == obj.id }
    }
}