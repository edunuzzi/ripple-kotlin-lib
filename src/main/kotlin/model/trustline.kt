import com.google.gson.annotations.SerializedName

data class GetTrustLinesParams(
    val account: String,
    val limit: Int = 10,
    val marker: Any? = null,
    @SerializedName("ledger_index") val ledgerIndex: Any? = "current",
    @SerializedName("ledger_hash") val ledgerHash: String? = null,
    val peer: String? = null,
    val command: String = "account_lines"
) : Param()

data class TrustLine(
    val account: String,
    val balance: String,
    val currency: String,
    val limit: String,
    @SerializedName("limit_peer") val limitPeer: String,
    @SerializedName("quality_in") val qualityIn: Int,
    @SerializedName("quality_out") val qualityOut: Int,
    @SerializedName("no_ripple") val noRipple: Boolean?,
    @SerializedName("no_ripple_peer") val noRipplePeer: Boolean?,
    val authorized: Boolean?,
    @SerializedName("peer_authorized") val peerAuthorized: Boolean?,
    val freeze: Boolean?,
    @SerializedName("freeze_peer") val freezePeer: Boolean?
)

data class GetTrustLinesResult(
    val account: String,
    val lines: List<TrustLine>,
    @SerializedName("ledger_current_index") val ledgerCurrentIndex: Int?,
    @SerializedName("ledger_index") val ledgerIndex: Int?,
    @SerializedName("ledger_hash") val ledgerHash: String?,
    val marker: Any
)

data class GetTrustLinesResponse(
    val result: GetTrustLinesResult
) : Response()