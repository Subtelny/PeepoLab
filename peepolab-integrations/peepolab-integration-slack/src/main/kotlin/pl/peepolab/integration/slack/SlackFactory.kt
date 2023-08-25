package pl.peepolab.integration.slack

import com.slack.api.app_backend.events.EventHandler
import com.slack.api.bolt.App
import com.slack.api.bolt.AppConfig
import com.slack.api.bolt.socket_mode.SocketModeApp
import io.micronaut.context.ApplicationContext
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import pl.peepolab.integration.slack.command.SlackCommandHandler

@Factory
class SlackFactory {

    private val log = LoggerFactory.getLogger(SlackFactory::class.simpleName)

    @Singleton
    fun createAppConfig(): AppConfig =
        AppConfig()

    @Singleton
    fun createApp(appConfig: AppConfig, applicationContext: ApplicationContext): SocketModeApp {
        val app = App(appConfig)
        log.info("Registering Slack command handlers")
        return SocketModeApp(app)
    }

}

