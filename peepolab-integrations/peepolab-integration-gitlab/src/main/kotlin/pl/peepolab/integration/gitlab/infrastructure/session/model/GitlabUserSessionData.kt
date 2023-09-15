package pl.peepolab.integration.gitlab.infrastructure.session.model

import pl.peepolab.integration.gitlab.model.GitlabUserId
import java.time.Instant

data class GitlabUserSessionData(
    val gitlabUserId: GitlabUserId,
    val accessToken: String,
    val refreshToken: String,
    val expireAt: Instant = Instant.EPOCH,
)