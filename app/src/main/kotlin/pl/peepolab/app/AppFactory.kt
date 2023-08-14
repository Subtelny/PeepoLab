package pl.peepolab.app

import com.slack.api.bolt.App
import com.slack.api.bolt.AppConfig
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton

@Factory
class AppFactory {

    @Singleton
    fun createAppConfig(): AppConfig =
        AppConfig()

    @Singleton
    fun createApp(appConfig: AppConfig): App {
        val app = App(appConfig)
        app.command("/hello") { req, ctx ->
            ctx.ack("What's up? ${req.payload.userName}!")
        }
        return app.start()
    }
}