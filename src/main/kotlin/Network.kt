data class Network(val baseURL: String){
    companion object {
        val Testnet = Network("wss://s.altnet.rippletest.net:51233")
        val Mainnet = Network("wss://s1.ripple.com")
    }
}