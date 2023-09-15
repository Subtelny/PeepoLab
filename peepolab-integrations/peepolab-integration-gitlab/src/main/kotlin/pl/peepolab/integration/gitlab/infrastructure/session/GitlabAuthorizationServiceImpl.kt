package pl.peepolab.integration.gitlab.infrastructure.session

import jakarta.inject.Singleton
import org.gitlab4j.api.GitLabApiException
import pl.peepolab.integration.gitlab.application.AuthorizationCode
import pl.peepolab.integration.gitlab.application.AuthorizationState
import pl.peepolab.integration.gitlab.application.GitlabAuthorizationService
import pl.peepolab.integration.gitlab.infrastructure.session.model.UserResult
import pl.peepolab.integration.gitlab.infrastructure.session.model.toSessionData
import pl.peepolab.integration.gitlab.model.CreateGitlabUserData
import pl.peepolab.integration.gitlab.model.GitlabUserId
import pl.peepolab.integration.gitlab.model.GitlabUserRepository
import pl.peepolab.integration.gitlab.model.exception.GitlabException
import pl.peepolab.module.model.user.model.CoreUserId

@Singleton
class GitlabAuthorizationServiceImpl(
    private val gitlabUserAuthorizer: GitlabUserAuthorizer,
    private val gitlabOauthStateStorage: GitlabOauthStateStorage,
    private val gitlabUserRepository: GitlabUserRepository,
    private val gitlabApiSessionProvider: GitlabApiSessionProvider,
) : GitlabAuthorizationService {

    override fun authorize(code: AuthorizationCode, state: AuthorizationState) {
        val coreUserId = processState(state)
        val authorizationResult = gitlabUserAuthorizer.authorize(code)

        val gitlabUserId = getOrCreateGitlabUser(coreUserId, authorizationResult.userInfo)
        val sessionData = authorizationResult.tokenInfo.toSessionData(gitlabUserId)
        gitlabApiSessionProvider.initializeSession(gitlabUserId, sessionData)
    }

    private fun getOrCreateGitlabUser(
        coreUserId: CoreUserId,
        userResult: UserResult,
    ): GitlabUserId {
        gitlabUserRepository.findGitlabUserByCoreUserId(coreUserId)?.let {
            return it.id
        }
        return gitlabUserRepository.createGitlabUser(
            CreateGitlabUserData(
                coreUserId = coreUserId,
                gitlabUserId = userResult.id,
                email = userResult.email,
                username = userResult.username,
            )
        ).id
    }

    private fun processState(state: AuthorizationState): CoreUserId {
        val coreUserId = gitlabOauthStateStorage.findByState(state.value)
            ?: throw IllegalStateException("Invalid state")
        gitlabOauthStateStorage.invalidateState(coreUserId)
        return coreUserId
    }

    override fun isAuthorized(gitlabUserId: GitlabUserId): Boolean =
        runCatching {
            gitlabApiSessionProvider.withUserSession(gitlabUserId) {
                it.userApi.currentUser != null
            }
        }.onFailure {
            when (it) {
                is GitLabApiException -> {
                    if (it.httpStatus == 401) {
                        return false
                    }
                }
                is GitlabException -> return false
                else -> throw it
            }
        }.getOrThrow()

}