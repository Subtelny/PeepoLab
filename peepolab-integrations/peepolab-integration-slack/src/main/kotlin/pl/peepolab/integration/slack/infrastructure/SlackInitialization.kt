package pl.peepolab.integration.slack.infrastructure

import com.slack.api.app_backend.events.EventHandler
import com.slack.api.bolt.socket_mode.SocketModeApp
import io.micronaut.context.ApplicationContext
import io.micronaut.context.event.ApplicationEventListener
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import pl.peepolab.integration.slack.application.cqs.SlackCommandHandler
import pl.peepolab.integration.slack.application.cqs.SlackQueryHandler
import pl.peepolab.integration.slack.ui.slashcommand.SlackSlashCommandHandler
import pl.peepolab.integration.slack.infrastructure.integration.SlackExternalIntegration
import pl.peepolab.module.api.event.CoreInitializedEvent
import pl.peepolab.module.api.integration.CoreContextExternalIntegrations
import pl.peepolab.utilities.cqs.MicronautHandlerRegistryBuilder

@Singleton
internal class SlackInitialization(
    private val socketModeApp: SocketModeApp,
    private val applicationContext: ApplicationContext,
    private val slackCommandQueryBus: SlackCommandQueryBus,
) : ApplicationEventListener<CoreInitializedEvent> {

    private val log = LoggerFactory.getLogger(SlackInitialization::class.simpleName)

    override fun onApplicationEvent(event: CoreInitializedEvent) {
        registerSlackBusHandlerRegistries()
        startSlackSocketMode()
        registerSlackExternalIntegration(event.source.getCoreIntegrations())
    }

    private fun registerSlackBusHandlerRegistries() =
        MicronautHandlerRegistryBuilder(applicationContext)
            .registerCommandHandlers(SlackCommandHandler::class.java)
            .registerQueryHandlers(SlackQueryHandler::class.java)
            .build()
            .run {
                slackCommandQueryBus.setupHandlerRegistry(this)
            }

    private fun registerSlackExternalIntegration(externalIntegrations: CoreContextExternalIntegrations) {
        val externalIntegration = SlackExternalIntegration()
        externalIntegrations.registerIntegration(externalIntegration)
    }

    private fun startSlackSocketMode() {
        applicationContext.getBeansOfType(SlackSlashCommandHandler::class.java)
            .forEach {
                log.info("Registering Slack command handler: {}", it.getCommand())
                socketModeApp.app.command(it.getCommand(), it)
            }
        applicationContext.getBeansOfType(EventHandler::class.java)
            .forEach {
                log.info("Registering Slack event handler: {}", it::class.simpleName)
                socketModeApp.app.event(it)
            }
        socketModeApp.startAsync()
    }

}