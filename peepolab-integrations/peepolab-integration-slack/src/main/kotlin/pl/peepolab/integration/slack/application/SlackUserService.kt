package pl.peepolab.integration.slack.application

import pl.peepolab.integration.slack.model.CreateSlackUserData
import pl.peepolab.integration.slack.model.SlackUser
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.integration.slack.model.exception.SlackUserException

interface SlackUserService {

    fun createSlackUser(data: CreateSlackUserData): SlackUser

    fun findSlackUser(slackUserId: SlackUserId): SlackUser?

    fun getSlackUser(slackUserId: SlackUserId): SlackUser = findSlackUser(slackUserId)
        ?: throw SlackUserException.NotFound(slackUserId)

}

