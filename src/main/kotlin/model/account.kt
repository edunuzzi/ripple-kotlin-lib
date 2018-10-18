import com.google.gson.annotations.SerializedName

data class GetAccountParams(
    val account: String,
    @SerializedName("signer_lists") val signerLists: Boolean = false,
    @SerializedName("ledger_index") val ledgerIndex: String = "current",
    val strict: String = "true",
    val command: String = "account_info"
) : Param()

data class Account(
    @SerializedName("Account") val account: String,
    @SerializedName("Balance") val balance: String,
    @SerializedName("Flags") val flags: Number,
    @SerializedName("LedgerEntryType") val ledgerEntryType: String,
    @SerializedName("OwnerCount") val ownerCount: Number,
    @SerializedName("PreviousTxnID") val previousTxnID: String,
    @SerializedName("PreviousTxnLgrSeq") val previousTxnLgrSeq: Number,
    @SerializedName("Sequence") val sequence: Number,
    val index: String,
    @SerializedName("AccountTxnID") val accountTxnID: String?,
    @SerializedName("Domain") val domain: String?,
    @SerializedName("EmailHash") val emailHash: String?,
    @SerializedName("MessageKey") val messageKey: String?,
    @SerializedName("RegularKey") val regularKey: String?,
    @SerializedName("TickSize") val tickSize: Int?,
    @SerializedName("TransferRate") val transferRate: Int?
)

data class SignerEntry(
    @SerializedName("Account") val account: String,
    @SerializedName("SignerWeight") val signerWeight: Int
)

data class SignerList(
    @SerializedName("LedgerEntryType") val ledgerEntryType: String,
    @SerializedName("Flags") val flags: Int,
    @SerializedName("PreviousTxnID") val previousTxnID: String,
    @SerializedName("PreviousTxnLgrSeq") val previousTxnLgrSeq: Int,
    @SerializedName("OwnerNode") val ownerNode: String,
    @SerializedName("SignerEntries") val signerEntries: List<SignerEntry>,
    @SerializedName("SignerListID") val signerListID: Int,
    @SerializedName("SignerQuorum") val signerQuorum: Int
)

data class GetAccountResult(
    @SerializedName("account_data") val accountData: Account,
    @SerializedName("signer_lists") val signerLists: List<SignerList>,
    @SerializedName("ledger_current_index") val ledgerCurrentIndex: Number?,
    @SerializedName("ledger_index") val ledgerIndex: Int?,
    val validated: Boolean?
)

data class GetAccountResponse(
    val result: GetAccountResult?
) : Response()
