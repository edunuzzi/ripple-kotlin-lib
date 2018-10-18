import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import org.json.JSONObject
import java.awt.SystemColor.text
import java.net.URI

class WebSocket : WebSocketClient {

    val res = BehaviorSubject.create<String>()
    val gson = Gson()

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
        // TODO Deal with me!!
        ex.printStackTrace()
    }

    inline fun <T:Param, reified S:Response>sendAndListen(obj: T): Observable<S> {
        super.send(gson.toJson(obj))

        // TODO Improve me!!
        return res
            .map {
                val err = gson.fromJson(it, ErrorResponse::class.java)

                if (err.status == Status.Error && err.id == obj.id) {
                    throw err
                }

                gson.fromJson(it, S::class.java)
            }
            .filter { it.id == obj.id }
    }
}