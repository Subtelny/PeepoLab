package pl.peepolab.integration.slack.ui.event

import com.slack.api.app_backend.events.handler.AppHomeOpenedHandler
import com.slack.api.app_backend.events.payload.AppHomeOpenedPayload
import pl.peepolab.integration.slack.application.SlackUserInteraction
import pl.peepolab.integration.slack.model.SlackUser
import pl.peepolab.integration.slack.model.SlackUserId

abstract class AppHomeOpenedSlackUserInteraction : AppHomeOpenedHandler(), SlackUserInteraction {

    override fun handle(payload: AppHomeOpenedPayload) {
        val rawUserId = payload.event.user
        val slackUserId = SlackUserId.of(rawUserId)
        retrieveSlackUser(slackUserId).run {
            handle(this, payload)
        }
    }

    abstract fun handle(invoker: SlackUser, payload: AppHomeOpenedPayload)
}