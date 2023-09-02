package pl.peepolab.integration.slack.application

import jakarta.inject.Singleton
import pl.peepolab.integration.slack.model.SlackUser
import pl.peepolab.integration.slack.model.SlackUserId

@Singleton
class SlackUserServiceImpl : SlackUserService {
    override fun findSlackUser(slackUserId: SlackUserId): SlackUser? {
        TODO("Not yet implemented")
    }
}