package pl.peepolab.integration.slack.infrastructure

import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import pl.peepolab.integration.slack.infrastructure.integration.SlackExternalIntegration
import pl.peepolab.module.api.ui.CoreService
import pl.peepolab.module.api.ui.command.RegisterExternalIntegrationCommand

@Singleton
class SlackIntegrationStartupEventListener(
    private val coreService: CoreService,
) {

//    @EventListener
    fun onStartup(event: StartupEvent) {
        val externalIntegration = SlackExternalIntegration()
        RegisterExternalIntegrationCommand(externalIntegration).let {
            coreService.execute(it)
        }
    }

}