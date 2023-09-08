package pl.peepolab.integration.slack.infrastructure.persistence.dao

import java.util.UUID

data class SlackUserEntity(
    val userId: UUID,
    val slackUserId: String,
    val email: String,
    val name: String,
    val realName: String,
    val avatar: String?,
)