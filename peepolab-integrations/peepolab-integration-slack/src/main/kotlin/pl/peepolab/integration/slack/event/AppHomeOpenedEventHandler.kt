package pl.peepolab.integration.slack.event

import com.slack.api.app_backend.events.handler.AppHomeOpenedHandler
import com.slack.api.app_backend.events.payload.AppHomeOpenedPayload
import com.slack.api.bolt.socket_mode.SocketModeApp
import jakarta.inject.Singleton
import java.io.BufferedReader
import java.io.InputStreamReader


private const val INTEGRATION_AVAILABLE_VIEW_FILE_NAME = "views/homeapp/integration_available.json"

@Singleton
class AppHomeOpenedEventHandler(
    private val socketModeApp: SocketModeApp,
) : AppHomeOpenedHandler() {

    private val view: String by lazy {
        val resource =
            AppHomeOpenedHandler::class.java.classLoader.getResourceAsStream(INTEGRATION_AVAILABLE_VIEW_FILE_NAME)!!

        val bufferedReader = BufferedReader(InputStreamReader(resource))
        bufferedReader.readText()
    }

    override fun handle(payload: AppHomeOpenedPayload) {
        val methods = socketModeApp.client.slack.methods(socketModeApp.app.config().singleTeamBotToken, payload.teamId)
        methods.viewsPublish {
            it.userId(payload.event.user)
                .viewAsString(view)
        }
    }
}