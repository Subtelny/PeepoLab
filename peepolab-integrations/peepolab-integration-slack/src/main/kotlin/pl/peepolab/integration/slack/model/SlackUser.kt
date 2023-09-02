package pl.peepolab.integration.slack.model

import pl.peepolab.module.model.user.model.UserId

data class SlackUser(
    val userId: UserId,
    val slackUserId: SlackUserId,
)
