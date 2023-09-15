package pl.peepolab.integration.gitlab.infrastructure.session

import com.github.benmanes.caffeine.cache.Caffeine
import jakarta.inject.Singleton
import pl.peepolab.module.model.user.model.CoreUserId
import java.util.UUID
import java.util.concurrent.TimeUnit

@Singleton
class GitlabOauthStateStorage {

    private val cache = Caffeine.newBuilder()
        .expireAfterAccess(1, TimeUnit.HOURS)
        .build<CoreUserId, UUID>()

    fun retrieveState(coreUserId: CoreUserId): UUID {
        return cache.get(coreUserId) { UUID.randomUUID() }
    }

    fun invalidateState(coreUserId: CoreUserId) {
        cache.invalidate(coreUserId)
    }

    fun findByState(state: UUID): CoreUserId? {
        return cache.asMap().entries.find { it.value == state }?.key
    }

}