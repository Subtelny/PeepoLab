package pl.peepolab.integration.gitlab.infrastructure.session.model

import com.fasterxml.jackson.annotation.JsonProperty
import pl.peepolab.integration.gitlab.model.GitlabUserId
import pl.peepolab.utilities.datatype.Email
import java.time.Instant

@JvmInline
value class AccessToken(val value: String)

data class AuthorizationResult(
    val tokenInfo: Oauth2TokenResponse,
    val userInfo: UserResult,
)

data class Oauth2TokenResponse(
    @JsonProperty("access_token") val accessToken: String,
    @JsonProperty("token_type") val tokenType: String,
    @JsonProperty("expires_in") val expiresIn: Long,
    @JsonProperty("refresh_token") val refreshToken: String,
    @JsonProperty("created_at") val createdAt: Long,
)

data class UserResult(
    val id: GitlabUserId,
    val email: Email,
    val username: String,
)


fun Oauth2TokenResponse.toSessionData(gitlabUserId: GitlabUserId) =
    GitlabUserSessionData(
        accessToken = accessToken,
        refreshToken = refreshToken,
        expireAt = Instant.ofEpochSecond(createdAt).plusSeconds(expiresIn),
        gitlabUserId = gitlabUserId,
    )