package pl.peepolab.integration.gitlab.infrastructure

import io.micronaut.context.event.ApplicationEventListener
import jakarta.inject.Singleton
import pl.peepolab.integration.gitlab.infrastructure.integration.GitlabExternalIntegration
import pl.peepolab.module.api.event.CoreInitializedEvent
import pl.peepolab.module.api.integration.CoreContextExternalIntegrations

@Singleton
class GitlabInitialization : ApplicationEventListener<CoreInitializedEvent> {

    override fun onApplicationEvent(event: CoreInitializedEvent) {
        registerGitlabExternalIntegration(event.source.getCoreIntegrations())
    }

    private fun registerGitlabExternalIntegration(externalIntegrations: CoreContextExternalIntegrations) {
        val gitlabExternalIntegration = GitlabExternalIntegration()
        externalIntegrations.registerIntegration(gitlabExternalIntegration)
    }

}