package pl.peepolab.module.core.infrastructure.persistence.user

import com.github.benmanes.caffeine.cache.Caffeine
import jakarta.inject.Singleton
import pl.peepolab.module.api.infrastructure.ConnectionProvider
import pl.peepolab.module.core.model.CoreUserRepository
import pl.peepolab.module.model.user.model.CoreUser
import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.datatype.Email
import java.util.*
import java.util.concurrent.TimeUnit

@Singleton
class CachedUserRepository(
    connectionProvider: ConnectionProvider,
) : CoreUserRepository {

    private val coreUserDao: CoreUserDao = CoreUserDao(connectionProvider)
    private val cache = Caffeine.newBuilder()
        .expireAfterAccess(1, TimeUnit.HOURS)
        .build<CoreUserId, CoreUser>()

    override fun createCoreUser(email: Email): CoreUser {
        val userId = CoreUserId(UUID.randomUUID())
        val user = CoreUser(
            id = userId,
            email = email,
        )
        coreUserDao.save(user.toEntity())
        return getCoreUser(userId)
    }

    override fun getCoreUser(coreUserId: CoreUserId): CoreUser {
        return cache.get(coreUserId) {
            coreUserDao.find(coreUserId)?.toModel()
        }
    }
}