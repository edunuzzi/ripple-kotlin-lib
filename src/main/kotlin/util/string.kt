import java.security.SecureRandom
import java.util.*

fun randomSecureString(size: Int = 512): String {
    val sr = SecureRandom()
    val b = ByteArray(size)

    sr.nextBytes(b)

    return Base64
        .getUrlEncoder()
        .withoutPadding()
        .encodeToString(b)
}