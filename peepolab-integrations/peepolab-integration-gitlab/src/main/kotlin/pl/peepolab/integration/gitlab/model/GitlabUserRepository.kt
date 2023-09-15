package pl.peepolab.integration.gitlab.model

import pl.peepolab.module.model.user.model.CoreUserId

interface GitlabUserRepository {

    fun createGitlabUser(data: CreateGitlabUserData): GitlabUser

    fun findGitlabUserByCoreUserId(coreUserId: CoreUserId): GitlabUser?

}