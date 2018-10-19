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

data class Transaction(
    val hash: String,
    @SerializedName("Account") val account: String,
    @SerializedName("TransactionType") val transactionType: TransactionType,
    @SerializedName("Destination") val destination: String? = null,
    @SerializedName("DestinationTag") val destinationTag: Int? = null,
    @SerializedName("Amount") val amount: Amount? = null,
    @SerializedName("Fee") val fee: String? = null,
    @SerializedName("Sequence") val sequence: Int? = null,
    @SerializedName("AccountTxnID") val accountTxnID: String? = null,
    @SerializedName("Flags") val flags: Number? = null,
    @SerializedName("Memos") val memos: List<Map<String, String>>? = null,
    @SerializedName("SourceTag") val sourceTag: Int? = null,
    @SerializedName("SigningPubKey") val signingPubKey: String? = null,
    @SerializedName("TxnSignature") val txnSignature: String? = null,
    @SerializedName("ledger_index") val ledgerIndex: Int? = null,
    val validated: Boolean? = null
)

data class GetTxInfoResponse(
    val result: Transaction?
) : Response()

data class SubmitTxParams(
    @SerializedName("tx_blob") val txBlob: String,
    val command: String = "submit"
) : Param()

enum class EngineResult {
    @SerializedName("tesSUCCESS")
    TesSUCCESS
}

data class SubmitTxResult(
    @SerializedName("engine_result") val engineResult: EngineResult,
    @SerializedName("engine_result_code") val engineResultCode: Int,
    @SerializedName("engine_result_message") val engineResultMessage: String,
    @SerializedName("tx_blob") val txBlob: String?,
    @SerializedName("tx_json") val txJson: Transaction?
)

data class SubmitTxResponse(
    val result: SubmitTxResult?
) : Response()

data class SubmitMultiSignedTxParams(
    @SerializedName("ts_json") val txJson: Transaction,
    @SerializedName("fail_hard") val failHard: Boolean? = null,
    val command: String = "submit_multisigned"
) : Param()

data class SubmitMultiSignedTxResponse(
    val result: SubmitTxResult?
) : Response()

data class SignTxParams(
    @SerializedName("tx_json") val txJson: Transaction,
    val secret: String? = null,
    val seed: String? = null,
    @SerializedName("seed_hex") val seedHex: String? = null,
    val passphrase: String? = null,
    @SerializedName("key_type") val keyType: KeyType? = null,
    val offline: Boolean = false,
    @SerializedName("fee_mult_max") val feeMultMax: Int? = null,
    @SerializedName("fee_div_max") val fee_div_max: Int? = null,
    val command: String = "sign"
) : Param()

data class SignTxResult(
    @SerializedName("ts_json") val txJson: Transaction,
    @SerializedName("tx_blob") val txBlob: String?
) : Response()

data class SignTxResponse(
    val result: SignTxResult?
) : Response()

data class SignTxForParams(
    val account: String,
    @SerializedName("tx_json") val txJson: Transaction,
    val secret: String? = null,
    val seed: String? = null,
    @SerializedName("seed_hex") val seedHex: String? = null,
    val passphrase: String? = null,
    @SerializedName("key_type") val keyType: KeyType? = null,
    val command: String = "sign_for"
) : Param()

data class SignTxForResponse(
    val result: SignTxResult?
) : Response()