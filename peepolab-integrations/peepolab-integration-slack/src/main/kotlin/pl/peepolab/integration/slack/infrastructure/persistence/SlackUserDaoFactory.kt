package pl.peepolab.integration.slack.infrastructure.persistence

import com.github.benmanes.caffeine.cache.Caffeine
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import pl.peepolab.integration.slack.model.SlackUser
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.module.api.infrastructure.ConnectionProvider
import pl.peepolab.utilities.dao.CachedDao
import pl.peepolab.utilities.dao.Dao
import java.util.concurrent.TimeUnit

@Factory
class SlackUserDaoFactory {

    @Bean
    fun slackUserDao(connectionProvider: ConnectionProvider): Dao<SlackUserId, SlackUser> {
        val slackUserDao = SlackUserDao(connectionProvider)
        val cache = Caffeine.newBuilder()
            .expireAfterAccess(1, TimeUnit.HOURS)
        return object : CachedDao<SlackUserId, SlackUser>(slackUserDao, cache) {
            override fun getModelId(model: SlackUser): SlackUserId {
                return model.slackUserId
            }
        }
    }

}