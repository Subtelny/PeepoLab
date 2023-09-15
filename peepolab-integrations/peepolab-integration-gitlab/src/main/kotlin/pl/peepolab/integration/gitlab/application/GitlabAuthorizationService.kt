package pl.peepolab.integration.gitlab.application

import pl.peepolab.integration.gitlab.model.GitlabUserId

interface GitlabAuthorizationService {

    fun authorize(code: AuthorizationCode, state: AuthorizationState)

    fun isAuthorized(gitlabUserId: GitlabUserId): Boolean

}