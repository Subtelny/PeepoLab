package pl.peepolab.integration.slack.model

import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.datatype.Email

data class SlackUser(
    val coreUserId: CoreUserId,
    val slackUserId: SlackUserId,
    val email: Email,
    val name: String,
    val realName: String,
    val avatar: String?,
)
