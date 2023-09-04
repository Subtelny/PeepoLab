package pl.peepolab.integration.slack.application

import pl.peepolab.integration.slack.model.SlackUser
import pl.peepolab.integration.slack.model.SlackUserId

interface SlackUserInteraction {

    val slackUserService: SlackUserService

    fun retrieveSlackUser(slackUserId: SlackUserId): SlackUser {
        return slackUserService.findSlackUser(slackUserId) ?: slackUserService.createSlackUser(
            CreateSlackUserData(
                slackUserId
            )
        )
    }

}