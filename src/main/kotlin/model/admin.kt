import com.google.gson.annotations.SerializedName

enum class KeyType {
    @SerializedName("secp256k1")
    Secp256k1,
    @SerializedName("ed25519")
    Ed25519,
}

data class GenerateKeypairParams(
    val passphrase: String? = null,
    val seed: String? = null,
    @SerializedName("seed_hex") val seedHex: String? = null,
    @SerializedName("key_type") val keyType: KeyType = KeyType.Secp256k1,
    val command: String = "wallet_propose"
) : Param()

data class GenerateKeypairResult(
    @SerializedName("master_seed") val masterSeed: String,
    @SerializedName("master_seed_hex") val masterSeedHex: String,
    @SerializedName("master_key") val masterKey: String,
    @SerializedName("account_id") val accountId: String,
    @SerializedName("public_key") val publicKey: String,
    @SerializedName("public_key_hex") val publicKeyHex: String,
    val warning: String?
)

data class GenerateKeypairResponse(
    val result: GenerateKeypairResult?
) : Response()

