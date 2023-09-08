package pl.peepolab.integration.slack.application

import pl.peepolab.integration.slack.model.exception.SlackUserException
import pl.peepolab.integration.slack.model.SlackUser
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.utilities.datatype.Email

interface SlackUserService {

    fun createSlackUser(data: CreateSlackUserData): SlackUser

    fun findSlackUser(slackUserId: SlackUserId): SlackUser?

    fun getSlackUser(slackUserId: SlackUserId): SlackUser = findSlackUser(slackUserId)
        ?: throw SlackUserException.NotFound(slackUserId.value)

}

data class CreateSlackUserData(
    val slackUserId: SlackUserId,
    val email: Email,
    val name: String,
    val realName: String,
    val avatar: String?,
)