package pl.peepolab.integration.gitlab.infrastructure

import io.micronaut.context.event.ApplicationEventListener
import jakarta.inject.Singleton
import pl.peepolab.integration.gitlab.application.GitlabAuthorizationService
import pl.peepolab.integration.gitlab.infrastructure.configuration.GitlabConfigurationProperties
import pl.peepolab.integration.gitlab.infrastructure.integration.GitlabExternalIntegration
import pl.peepolab.integration.gitlab.infrastructure.session.GitlabOauthStateStorage
import pl.peepolab.integration.gitlab.model.GitlabUserRepository
import pl.peepolab.module.api.event.CoreInitializedEvent
import pl.peepolab.module.api.integration.CoreContextExternalIntegrations

@Singleton
class GitlabInitialization(
    private val gitlabUserRepository: GitlabUserRepository,
    private val gitlabAuthorizationService: GitlabAuthorizationService,
    private val gitlabConfiguration: GitlabConfigurationProperties,
    private val gitlabOauthStateStorage: GitlabOauthStateStorage,
) : ApplicationEventListener<CoreInitializedEvent> {

    override fun onApplicationEvent(event: CoreInitializedEvent) {
        registerGitlabExternalIntegration(event.source.getCoreIntegrations())
    }

    private fun registerGitlabExternalIntegration(externalIntegrations: CoreContextExternalIntegrations) {
        val gitlabExternalIntegration = getGitlabExternalIntegration()
        externalIntegrations.registerIntegration(gitlabExternalIntegration)
    }

    private fun getGitlabExternalIntegration() =
        GitlabExternalIntegration(
            gitlabUserRepository = gitlabUserRepository,
            gitlabAuthorizationService = gitlabAuthorizationService,
            gitlabConfiguration = gitlabConfiguration,
            gitlabOauthStateStorage = gitlabOauthStateStorage,
        )

}