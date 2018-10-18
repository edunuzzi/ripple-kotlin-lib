import com.google.gson.annotations.SerializedName

enum class TransactionType {
    Payment,
    OfferCreate,
    OfferCancel,
    TrustSet,
    AccountSet,
    SetRegularKey,
    SignerListSet,
    EscrowCreate,
    EscrowFinish,
    EscrowCancel,
    PaymentChannelCreate,
    PaymentChannelFund,
    PaymentChannelClaim
}

data class GetTxInfoParams(
    val transaction: String,
    val command: String = "tx"
) : Param()

data class Amount(
    val currency: String,
    val value: String,
    val issuer: String
)

data class GetTxInfoResult(
    val hash: String,
    @SerializedName("Account") val account: String,
    @SerializedName("TransactionType") val transactionType: TransactionType,
    @SerializedName("Destination") val destination: String?,
    @SerializedName("Amount") val amount: Amount?,
    @SerializedName("Fee") val fee: String,
    @SerializedName("Sequence") val sequence: Int,
    @SerializedName("AccountTxnID") val accountTxnID: String?,
    @SerializedName("Flags") val flags: Number,
    @SerializedName("Memos") val memos: List<Map<String, String>>,
    @SerializedName("SourceTag") val sourceTag: Int,
    @SerializedName("SigningPubKey") val signingPubKey: String?,
    @SerializedName("TxnSignature") val txnSignature: String?,
    @SerializedName("ledger_index") val ledgerIndex: Int,
    val validated: Boolean
)

data class GetTxInfoResponse(
    val result: GetTxInfoResult
) : Response()

data class SubmitTxParams(
    @SerializedName("tx_blob") val txBlob: String,
    val command: String = "submit"
) : Param()

enum class EngineResult {
    @SerializedName("tesSUCCESS") TesSUCCESS
}

data class SubmitTxResult(
    @SerializedName("engine_result") val engineResult: EngineResult,
    @SerializedName("engine_result_code") val engineResultCode: Int,
    @SerializedName("engine_result_message") val engineResultMessage: String,
    @SerializedName("tx_blob") val txBlob: String
)

data class SubmitTxResponse(
    val result: SubmitTxResult
) : Response()