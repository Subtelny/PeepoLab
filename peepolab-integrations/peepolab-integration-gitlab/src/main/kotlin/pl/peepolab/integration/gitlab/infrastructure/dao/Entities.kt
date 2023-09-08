package pl.peepolab.integration.gitlab.infrastructure.dao

import pl.peepolab.integration.gitlab.model.GitlabUser
import pl.peepolab.integration.gitlab.model.GitlabUserId
import pl.peepolab.integration.gitlab.model.GitlabUserSession
import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.datatype.Email
import java.util.*

data class GitlabUserEntity(
    val id: Long,
    val userId: UUID,
    val email: String,
    val username: String,
)

data class GitlabUserSessionEntity(
    val gitlabUserId: Long,
    val accessToken: String,
    val refreshToken: String,
)

fun GitlabUserEntity.toModel() = GitlabUser(
    id = GitlabUserId.of(id),
    userId = CoreUserId.of(userId),
    email = Email(email),
    username = username,
)

fun GitlabUserSessionEntity.toModel() = GitlabUserSession(
    accessToken = accessToken,
    refreshToken = refreshToken,
)

fun GitlabUser.toEntity() = GitlabUserEntity(
    id = id.value,
    userId = userId.value,
    email = email.value,
    username = username,
)

fun GitlabUserSession.toEntity(userId: GitlabUserId) = GitlabUserSessionEntity(
    gitlabUserId = userId.value,
    accessToken = accessToken,
    refreshToken = refreshToken,
)