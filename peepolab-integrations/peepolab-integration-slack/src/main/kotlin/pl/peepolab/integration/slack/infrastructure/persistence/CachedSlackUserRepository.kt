package pl.peepolab.integration.slack.infrastructure.persistence

import com.github.benmanes.caffeine.cache.Caffeine
import jakarta.inject.Singleton
import pl.peepolab.integration.slack.application.CreateSlackUserData
import pl.peepolab.integration.slack.infrastructure.persistence.dao.SlackUserDao
import pl.peepolab.integration.slack.infrastructure.persistence.dao.toEntity
import pl.peepolab.integration.slack.infrastructure.persistence.dao.toModel
import pl.peepolab.integration.slack.model.SlackUser
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.integration.slack.model.SlackUserRepository
import pl.peepolab.integration.slack.model.exception.SlackUserException
import pl.peepolab.module.api.infrastructure.ConnectionProvider
import pl.peepolab.module.model.user.model.CoreUserId
import java.util.concurrent.TimeUnit

@Singleton
class CachedSlackUserRepository(
    connectionProvider: ConnectionProvider,
) : SlackUserRepository {

    private val slackUserDao: SlackUserDao = SlackUserDao(connectionProvider)
    private val cache = Caffeine.newBuilder()
        .expireAfterAccess(1, TimeUnit.HOURS)
        .build<SlackUserId, SlackUser>()

    override fun createSlackUser(userId: CoreUserId, data: CreateSlackUserData): SlackUser {
        val entity = data.toEntity(userId)
        slackUserDao.save(entity)
        return getSlackUser(data.slackUserId)
    }

    override fun findSlackUser(slackUserId: SlackUserId): SlackUser? {
        return cache.get(slackUserId) {
            slackUserDao.find(slackUserId)?.toModel()
        }
    }

    override fun getSlackUser(slackUserId: SlackUserId): SlackUser {
        return findSlackUser(slackUserId) ?: throw SlackUserException.NotFound(slackUserId.value)
    }

}