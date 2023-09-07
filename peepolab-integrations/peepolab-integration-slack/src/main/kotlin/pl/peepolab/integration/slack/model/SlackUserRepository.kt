package pl.peepolab.integration.slack.model

import pl.peepolab.integration.slack.application.CreateSlackUserData
import pl.peepolab.module.model.user.model.UserId

interface SlackUserRepository {

    fun createSlackUser(userId: UserId, data: CreateSlackUserData): SlackUser

    fun findSlackUser(slackUserId: SlackUserId): SlackUser?

    fun getSlackUser(slackUserId: SlackUserId): SlackUser

}