package pl.peepolab.integration.gitlab.model

import java.time.LocalDateTime

private const val EXPIRE_TOKEN_OFFSET_MINUTES = 5L

data class GitlabToken(
    val accessToken: String,
    val refreshToken: String,
    val expireAt: LocalDateTime,
) {

    fun isExpired() = expireAt.isAfter(LocalDateTime.now().minusMinutes(EXPIRE_TOKEN_OFFSET_MINUTES))

}