package pl.peepolab.slack

import com.slack.api.app_backend.events.EventHandler
import com.slack.api.bolt.App
import com.slack.api.bolt.AppConfig
import com.slack.api.bolt.socket_mode.SocketModeApp
import io.micronaut.context.ApplicationContext
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import pl.peepolab.slack.command.SlackCommandHandler

@Factory
class SlackFactory {

    private val log = LoggerFactory.getLogger(SlackFactory::class.java)

    @Singleton
    fun createAppConfig(): AppConfig =
        AppConfig()

    @Singleton
    fun createApp(appConfig: AppConfig, applicationContext: ApplicationContext): SocketModeApp {
        val app = App(appConfig)
        log.info("Registering Slack command handlers")
        applicationContext.getBeansOfType(SlackCommandHandler::class.java)
            .forEach {
                log.info("Registering Slack command handler: {}", it.getCommand())
                app.command(it.getCommand(), it)
            }
        applicationContext.getBeansOfType(EventHandler::class.java)
            .forEach {
                app.event(it)
            }
        return SocketModeApp(app)
    }

}

