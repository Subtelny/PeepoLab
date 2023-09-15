package pl.peepolab.integration.gitlab.infrastructure.persistence.dao

import pl.peepolab.integration.gitlab.infrastructure.session.model.GitlabUserSessionData
import pl.peepolab.integration.gitlab.model.CreateGitlabUserData
import pl.peepolab.integration.gitlab.model.GitlabUser
import pl.peepolab.integration.gitlab.model.GitlabUserId
import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.datatype.Email

fun GitlabUserEntity.toModel() = GitlabUser(
    id = GitlabUserId.of(id),
    coreUserId = CoreUserId.of(coreUserId),
    email = Email(email),
    username = username,
)

fun GitlabUser.toEntity() = GitlabUserEntity(
    id = id.value,
    coreUserId = coreUserId.value,
    email = email.value,
    username = username,
)

fun GitlabUserSessionDataEntity.toModel() = GitlabUserSessionData(
    gitlabUserId = GitlabUserId.of(gitlabUserId),
    accessToken = accessToken,
    refreshToken = refreshToken,
)

fun GitlabUserSessionData.toEntity() = GitlabUserSessionDataEntity(
    gitlabUserId = gitlabUserId.value,
    accessToken = accessToken,
    refreshToken = refreshToken,
)

fun CreateGitlabUserData.toEntity() = GitlabUserEntity(
    id = gitlabUserId.value,
    coreUserId = coreUserId.value,
    email = email.value,
    username = username,
)