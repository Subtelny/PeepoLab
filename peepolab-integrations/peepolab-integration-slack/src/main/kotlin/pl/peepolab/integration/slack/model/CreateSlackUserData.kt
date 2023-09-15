package pl.peepolab.integration.slack.model

import pl.peepolab.utilities.datatype.Email

data class CreateSlackUserData(
    val slackUserId: SlackUserId,
    val email: Email,
    val name: String,
    val realName: String,
    val avatar: String?,
)