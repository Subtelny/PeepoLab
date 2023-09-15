package pl.peepolab.integration.gitlab.infrastructure.persistence.dao

import java.util.*

data class GitlabUserEntity(
    val id: Long,
    val coreUserId: UUID,
    val email: String,
    val username: String,
)

data class GitlabUserSessionDataEntity(
    val gitlabUserId: Long,
    val accessToken: String,
    val refreshToken: String,
)