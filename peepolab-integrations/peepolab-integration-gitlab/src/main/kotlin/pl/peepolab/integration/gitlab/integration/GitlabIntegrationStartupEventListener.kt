package pl.peepolab.integration.gitlab.integration

import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import pl.peepolab.module.api.ui.CoreService
import pl.peepolab.module.api.ui.command.RegisterExternalIntegrationCommand

@Singleton
class GitlabIntegrationStartupEventListener(
    private val coreService: CoreService,
) {
//    @EventListener
    fun onStartup(event: StartupEvent) {
        val externalIntegration = GitlabExternalIntegration()
        RegisterExternalIntegrationCommand(externalIntegration).let {
            coreService.execute(it)
        }
    }

}