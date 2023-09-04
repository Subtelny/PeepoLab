package pl.peepolab.integration.slack.application

import jakarta.inject.Singleton
import pl.peepolab.integration.slack.infrastructure.persistence.SlackUserDao
import pl.peepolab.integration.slack.model.SlackUser
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.utilities.dao.Dao

@Singleton
class SlackUserServiceImpl(
    private val slackUserDao: Dao<SlackUserId, SlackUser>,
) : SlackUserService {

    override fun createSlackUser(data: CreateSlackUserData): SlackUser {
        TODO("Not yet implemented")
    }

    override fun findSlackUser(slackUserId: SlackUserId): SlackUser? {
        return slackUserDao.find(slackUserId)
    }


}