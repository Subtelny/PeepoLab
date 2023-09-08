package pl.peepolab.integration.slack.infrastructure.persistence.dao

import pl.peepolab.integration.slack.application.CreateSlackUserData
import pl.peepolab.integration.slack.model.SlackUser
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.datatype.Email


internal fun SlackUserEntity.toModel(): SlackUser {
    return SlackUser(
        userId = CoreUserId.of(userId),
        slackUserId = SlackUserId.of(slackUserId),
        email = Email(email),
        name = name,
        realName = realName,
        avatar = avatar
    )
}

internal fun CreateSlackUserData.toEntity(userId: CoreUserId): SlackUserEntity {
    return SlackUserEntity(
        userId = userId.value,
        slackUserId = slackUserId.value,
        email = email.value,
        name = name,
        realName = realName,
        avatar = avatar
    )
}