import java.net.URI

class Server(network: Network) {

    val ws = WebSocket(URI(network.baseURL))

    init {
        ws.connectBlocking()
    }

    fun close() {
        ws.closeBlocking()
    }
}