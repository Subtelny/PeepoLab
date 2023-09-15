package pl.peepolab.integration.gitlab.infrastructure.session

import com.github.benmanes.caffeine.cache.Caffeine
import jakarta.inject.Singleton
import org.gitlab4j.api.Constants
import org.gitlab4j.api.GitLabApi
import org.gitlab4j.api.GitLabApiException
import pl.peepolab.integration.gitlab.infrastructure.configuration.GitlabConfigurationProperties
import pl.peepolab.integration.gitlab.infrastructure.persistence.CachedGitlabUserSessionDataRepository
import pl.peepolab.integration.gitlab.infrastructure.session.gitlabapi.Oauth2GitLabApi
import pl.peepolab.integration.gitlab.infrastructure.session.gitlabapi.Oauth2SessionApi
import pl.peepolab.integration.gitlab.infrastructure.session.model.GitlabSession
import pl.peepolab.integration.gitlab.infrastructure.session.model.GitlabUserSessionData
import pl.peepolab.integration.gitlab.infrastructure.session.model.toSessionData
import pl.peepolab.integration.gitlab.model.GitlabUserId
import pl.peepolab.integration.gitlab.model.exception.GitlabException
import java.util.concurrent.TimeUnit

@Singleton
class GitlabApiSessionProvider(
    private val gitlabConfiguration: GitlabConfigurationProperties,
    private val sessionDataRepository: CachedGitlabUserSessionDataRepository,
) {

    private val cache = Caffeine.newBuilder()
        .expireAfterAccess(1, TimeUnit.HOURS)
        .build<GitlabUserId, GitlabSession>()

    fun <T> withUserSession(gitlabUserId: GitlabUserId, block: (session: GitLabApi) -> T): T {
        fun executeWithSession(): T {
            val session = retrieveGitLabApi(gitlabUserId)
            return if (session.needsRefresh()) {
                refreshSession(gitlabUserId)
                block(retrieveGitLabApi(gitlabUserId).gitlabApi)
            } else {
                block(session.gitlabApi)
            }
        }
        return try {
            executeWithSession()
        } catch (e: GitLabApiException) {
            if (e.httpStatus == 401) {
                refreshSession(gitlabUserId)
                executeWithSession()
            } else {
                throw e
            }
        }
    }

    internal fun initializeSession(gitlabUserId: GitlabUserId, sessionData: GitlabUserSessionData) {
        sessionDataRepository.saveSessionData(sessionData)
        cache.put(gitlabUserId, GitlabSession(constructGitLabApi(sessionData.accessToken), sessionData.expireAt))
    }

    private fun retrieveGitLabApi(gitlabUserId: GitlabUserId): GitlabSession {
        return cache.get(gitlabUserId) {
            val sessionData = getSessionData(it)
            val gitlabApi = constructGitLabApi(sessionData.accessToken)
            GitlabSession(gitlabApi, sessionData.expireAt)
        }
    }

    private fun getSessionData(gitlabUserId: GitlabUserId): GitlabUserSessionData {
        return sessionDataRepository.findSessionData(gitlabUserId)
            ?: throw GitlabException.NotAuthorized()
    }

    private fun refreshSession(gitlabUserId: GitlabUserId) {
        val sessionData = getSessionData(gitlabUserId)
        val tokenResponse = Oauth2GitLabApi(gitlabConfiguration).sessionApi.refreshToken(sessionData.refreshToken)
        initializeSession(gitlabUserId, tokenResponse.toSessionData(gitlabUserId))
    }

    private fun constructGitLabApi(token: String) =
        GitLabApi(
            GitLabApi.ApiVersion.V4,
            gitlabConfiguration.gitlabUrl,
            Constants.TokenType.OAUTH2_ACCESS,
            token,
            null,
            emptyMap(),
        )

}