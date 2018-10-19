import java.net.URI

abstract class BaseServer(network: Network) {

    val ws = WebSocket(URI(network.baseURL))

    init {
        ws.connectBlocking()
    }

    fun close() {
        ws.closeBlocking()
    }
}

class AdminServer(network: Network) : BaseServer(network)
class Server(network: Network) : BaseServer(network)