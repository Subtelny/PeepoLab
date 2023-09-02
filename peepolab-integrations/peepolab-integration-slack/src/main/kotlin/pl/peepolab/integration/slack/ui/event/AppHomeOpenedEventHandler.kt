package pl.peepolab.integration.slack.ui.event

import com.slack.api.app_backend.events.handler.AppHomeOpenedHandler
import com.slack.api.app_backend.events.payload.AppHomeOpenedPayload
import com.slack.api.bolt.socket_mode.SocketModeApp
import com.slack.api.model.view.View
import jakarta.inject.Singleton
import pl.peepolab.integration.slack.application.cqs.SlackQueryBus
import pl.peepolab.integration.slack.application.view.home.GetAppHomeViewSlackQuery
import pl.peepolab.integration.slack.model.SlackUserId

@Singleton
class AppHomeOpenedEventHandler(
    private val socketModeApp: SocketModeApp,
    private val slackQueryBus: SlackQueryBus,
) : AppHomeOpenedHandler() {

    override fun handle(payload: AppHomeOpenedPayload) {
        val resultView = createAppHomeView(payload)
        publishAppHomeView(payload, resultView)
    }

    private fun publishAppHomeView(
        payload: AppHomeOpenedPayload,
        resultView: View,
    ) {
        val methods = socketModeApp.client.slack.methods(socketModeApp.app.config().singleTeamBotToken, payload.teamId)
        methods.viewsPublish {
            it.userId(payload.event.user)
                .view(resultView)
        }
    }

    private fun createAppHomeView(payload: AppHomeOpenedPayload): View {
        val slackUserId = SlackUserId(payload.event.user)
        return slackQueryBus.executeQuery(GetAppHomeViewSlackQuery(slackUserId))
    }
}