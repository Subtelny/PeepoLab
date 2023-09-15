package pl.peepolab.integration.gitlab.infrastructure.session.gitlabapi

import org.gitlab4j.api.GitLabApi
import pl.peepolab.integration.gitlab.infrastructure.configuration.GitlabConfigurationProperties

class Oauth2GitLabApi(
    gitlabConfiguration: GitlabConfigurationProperties,
) : GitLabApi(ApiVersion.V4, gitlabConfiguration.gitlabUrl, null) {

    val sessionApi: Oauth2SessionApi = Oauth2SessionApi(this, gitlabConfiguration)

}