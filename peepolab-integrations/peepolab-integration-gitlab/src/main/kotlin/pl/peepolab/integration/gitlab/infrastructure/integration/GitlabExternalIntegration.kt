package pl.peepolab.integration.gitlab.infrastructure.integration

import pl.peepolab.integration.gitlab.application.GitlabAuthorizationService
import pl.peepolab.integration.gitlab.infrastructure.configuration.GitlabConfigurationProperties
import pl.peepolab.integration.gitlab.infrastructure.session.GitlabOauthStateStorage
import pl.peepolab.integration.gitlab.model.GitlabUserRepository
import pl.peepolab.module.api.integration.ExternalIntegration
import pl.peepolab.module.api.integration.strategy.ExternalIntegrationAuthStrategy
import pl.peepolab.module.model.integration.ExternalUserIntegrationStatus
import pl.peepolab.module.model.integration.ExternalIntegrationType
import pl.peepolab.module.model.user.model.CoreUserId

class GitlabExternalIntegration(
    private val gitlabUserRepository: GitlabUserRepository,
    private val gitlabAuthorizationService: GitlabAuthorizationService,
    private val gitlabConfiguration: GitlabConfigurationProperties,
    private val gitlabOauthStateStorage: GitlabOauthStateStorage,
) : ExternalIntegration.VersionControl {

    override fun getExternalUserIntegrationStatus(coreUserId: CoreUserId): ExternalUserIntegrationStatus {
        val gitlabUser = gitlabUserRepository.findGitlabUserByCoreUserId(coreUserId)
            ?: return ExternalUserIntegrationStatus.NotIntegrated

        if (gitlabAuthorizationService.isAuthorized(gitlabUser.id)) {
            return ExternalUserIntegrationStatus.IntegratedAndAuthorized
        }
        return ExternalUserIntegrationStatus.IntegratedNotAuthorized
    }

    override fun getExternalIntegrationAuthStrategy(coreUserId: CoreUserId): ExternalIntegrationAuthStrategy {
        return ExternalIntegrationAuthStrategy.OIDC(
            authorizationEndpoint = gitlabConfiguration.gitlabUrl + "/oauth/authorize",
            scopes = gitlabConfiguration.scopes,
            clientId = gitlabConfiguration.clientId,
            redirectUri = gitlabConfiguration.redirectUrl,
            state = gitlabOauthStateStorage.retrieveState(coreUserId).toString()
        )
    }

    override fun getType(): ExternalIntegrationType = ExternalIntegrationType("GITLAB")

}