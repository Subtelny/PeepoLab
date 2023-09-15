package pl.peepolab.integration.slack.model

import pl.peepolab.module.model.user.model.CoreUserId

interface SlackUserRepository {

    fun createSlackUser(userId: CoreUserId, data: CreateSlackUserData): SlackUser

    fun findSlackUser(slackUserId: SlackUserId): SlackUser?

    fun getSlackUser(slackUserId: SlackUserId): SlackUser

}