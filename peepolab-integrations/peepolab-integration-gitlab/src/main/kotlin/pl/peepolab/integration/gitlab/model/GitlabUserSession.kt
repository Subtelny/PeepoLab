package pl.peepolab.integration.gitlab.model

private const val EXPIRE_TOKEN_OFFSET_MINUTES = 5L

data class GitlabUserSession(
    val accessToken: String,
    val refreshToken: String,
) {

    //fun isExpired() = expireAt.isAfter(LocalDateTime.now().minusMinutes(EXPIRE_TOKEN_OFFSET_MINUTES))

}