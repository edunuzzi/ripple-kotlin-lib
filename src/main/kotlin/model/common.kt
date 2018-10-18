import com.google.gson.annotations.SerializedName

open class Param {
    val id: String = randomSecureString(512)
}

open class Response {
    open lateinit var id: String
}

enum class Status {
    @SerializedName("error") Error
}

enum class ErrorCode {
    @SerializedName("unknownCmd") UnknownCmd,
    @SerializedName("jsonInvalid") JsonInvalid,
    @SerializedName("missingCommand") MissingCommand,
    @SerializedName("tooBusy") TooBusy,
    @SerializedName("noNetwork") NoNetwork,
    @SerializedName("noCurrent") NoCurrent,
    @SerializedName("noClosed") NoClosed,
    @SerializedName("wsTextRequired") WsTextRequired,
    @SerializedName("actMalformed") ActMalformed,
    @SerializedName("invalidParams") InvalidParams,
    @SerializedName("actNotFound") ActNotFound,
    @SerializedName("lgrNotFound") LgrNotFound,
    @SerializedName("txnNotFound") TxnNotFound,
    @SerializedName("amendmentBlocked") AmendmentBlocked,

    @SerializedName("highFee") HighFee,
    @SerializedName("internalJson") InternalJson,
    @SerializedName("internalSubmit") InternalSubmit,
    @SerializedName("internalTransaction") InternalTransaction,
    @SerializedName("invalidTransaction") InvalidTransaction,
    @SerializedName("noPath") NoPath,
    @SerializedName("internal") Internal,
    @SerializedName("srcActMalformed") SrcActMalformed,
}

data class ErrorResponse(
    val status: Status,
    val error: ErrorCode?,
    val id: String,
    @SerializedName("error_code") val errorCode: Int?,
    @SerializedName("error_message") val errorMessage: String?,
    val request: Any
) : Throwable(errorMessage)