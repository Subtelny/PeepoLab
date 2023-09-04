package pl.peepolab.integration.slack.model

import pl.peepolab.module.model.user.model.UserId
import pl.peepolab.utilities.datatype.Email

data class SlackUser(
    val userId: UserId,
    val slackUserId: SlackUserId,
    val email: Email,
)
