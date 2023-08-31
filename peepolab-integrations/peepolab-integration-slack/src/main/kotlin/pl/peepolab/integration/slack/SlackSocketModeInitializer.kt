package pl.peepolab.integration.slack

import com.slack.api.app_backend.events.EventHandler
import com.slack.api.bolt.socket_mode.SocketModeApp
import io.micronaut.context.ApplicationContext
import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import pl.peepolab.integration.slack.application.cqs.command.SlackSlashCommandHandler

@Singleton
class SlackSocketModeInitializer(
    private val socketModeApp: SocketModeApp,
    private val applicationContext: ApplicationContext,
) {

    private val log = LoggerFactory.getLogger(SlackFactory::class.simpleName)

    @EventListener
    fun onStartup(event: StartupEvent?) {
        applicationContext.getBeansOfType(SlackSlashCommandHandler::class.java)
            .forEach {
                log.info("Registering Slack command handler: {}", it.getCommand())
                socketModeApp.app.command(it.getCommand(), it)
            }
        applicationContext.getBeansOfType(EventHandler::class.java)
            .forEach {
                socketModeApp.app.event(it)
            }
        socketModeApp.startAsync()
    }

}