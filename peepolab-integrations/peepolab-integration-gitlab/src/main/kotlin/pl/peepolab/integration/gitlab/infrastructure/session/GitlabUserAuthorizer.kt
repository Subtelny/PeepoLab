package pl.peepolab.integration.gitlab.infrastructure.session

import jakarta.inject.Singleton
import org.gitlab4j.api.Constants
import org.gitlab4j.api.GitLabApi
import org.gitlab4j.api.models.User
import pl.peepolab.integration.gitlab.infrastructure.configuration.GitlabConfigurationProperties
import pl.peepolab.integration.gitlab.infrastructure.session.gitlabapi.Oauth2SessionApi
import pl.peepolab.integration.gitlab.application.AuthorizationCode
import pl.peepolab.integration.gitlab.infrastructure.session.gitlabapi.Oauth2GitLabApi
import pl.peepolab.integration.gitlab.infrastructure.session.model.AuthorizationResult
import pl.peepolab.integration.gitlab.infrastructure.session.model.UserResult
import pl.peepolab.integration.gitlab.model.GitlabUserId
import pl.peepolab.utilities.datatype.Email

@Singleton
class GitlabUserAuthorizer(
    private val gitlabConfiguration: GitlabConfigurationProperties,
) {
    fun authorize(code: AuthorizationCode): AuthorizationResult {
        val tempGitlabApi = Oauth2GitLabApi(gitlabConfiguration)
        val token = tempGitlabApi.sessionApi.authorize(code)

        val gitlabApi = constructGitLabApi(token.accessToken)
        val userResult = gitlabApi.userApi.currentUser.toResult()

        return AuthorizationResult(
            tokenInfo = token,
            userInfo = userResult,
        )
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


    private fun User.toResult() = UserResult(
        id = GitlabUserId.of(id),
        email = Email(email),
        username = username,
    )
}