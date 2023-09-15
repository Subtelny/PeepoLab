package pl.peepolab.integration.gitlab.infrastructure.session.gitlabapi

import jakarta.ws.rs.core.Response
import org.gitlab4j.api.*
import pl.peepolab.integration.gitlab.application.AuthorizationCode
import pl.peepolab.integration.gitlab.infrastructure.configuration.GitlabConfigurationProperties
import pl.peepolab.integration.gitlab.infrastructure.session.model.Oauth2TokenResponse
import java.net.URL

class Oauth2SessionApi(
    gitlabApi: GitLabApi,
    private val gitlabConfiguration: GitlabConfigurationProperties,
) : AbstractApi(gitlabApi) {

    private val apiClient = OauthApiClient(gitlabConfiguration.gitlabUrl)
    override fun getApiClient(): GitLabApiClient {
        return apiClient
    }

    @Throws(GitLabApiException::class)
    fun authorize(code: AuthorizationCode): Oauth2TokenResponse {
        val formData: GitLabApiForm = GitLabApiForm()
            .withParam("client_id", gitlabConfiguration.clientId)
            .withParam("client_secret", gitlabConfiguration.clientSecret)
            .withParam("code", code.value)
            .withParam("grant_type", "authorization_code")
            .withParam("redirect_uri", gitlabConfiguration.redirectUrl)
        val post = post(Response.Status.OK, formData, "oauth", "token")
        return post.readEntity(Oauth2TokenResponse::class.java)
    }

    @Throws(GitLabApiException::class)
    fun refreshToken(refreshToken: String): Oauth2TokenResponse {
        val formData: GitLabApiForm = GitLabApiForm()
            .withParam("client_id", gitlabConfiguration.clientId)
            .withParam("client_secret", gitlabConfiguration.clientSecret)
            .withParam("refresh_token", refreshToken)
            .withParam("grant_type", "refresh_token")
            .withParam("redirect_uri", gitlabConfiguration.redirectUrl)
        val post = post(Response.Status.OK, formData, "oauth", "token")
        return post.readEntity(Oauth2TokenResponse::class.java)
    }

}

class OauthApiClient(hostUrl: String) : GitLabApiClient(
    GitLabApi.ApiVersion.V4,
    hostUrl,
    Constants.TokenType.OAUTH2_ACCESS,
    null,
    null,
    null
) {

    override fun getApiUrl(vararg pathArgs: Any?): URL {
        return getUrlWithBase(*pathArgs)
    }



}
