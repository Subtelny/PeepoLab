package pl.peepolab.integration.slack.application

import pl.peepolab.integration.slack.model.SlackUser
import pl.peepolab.integration.slack.model.SlackUserId

interface SlackUserService {

    fun createSlackUser(data: CreateSlackUserData): SlackUser

    fun findSlackUser(slackUserId: SlackUserId): SlackUser?

    fun getSlackUser(slackUserId: SlackUserId): SlackUser = findSlackUser(slackUserId)
        ?: throw IllegalStateException("Slack user with id $slackUserId not found")

}

data class CreateSlackUserData(
    val slackUserId: SlackUserId,
)