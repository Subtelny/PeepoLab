package pl.peepolab.module.core.infrastructure.persistence.user

import com.github.benmanes.caffeine.cache.Caffeine
import jakarta.inject.Singleton
import pl.peepolab.module.api.infrastructure.ConnectionProvider
import pl.peepolab.module.core.model.CoreUserRepository
import pl.peepolab.module.model.user.exception.UserException
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

    private val emailCache = Caffeine.newBuilder()
        .expireAfterAccess(1, TimeUnit.HOURS)
        .build<Email, CoreUserId>()

    override fun createCoreUser(email: Email): CoreUser {
        if (findIdByEmail(email) != null) {
            throw UserException.AlreadyExists(email)
        }
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
            coreUserDao.find(it)?.toModel()
        }
    }

    override fun getCoreUser(email: Email): CoreUser {
        val coreUserId = findIdByEmail(email) ?: throw UserException.NotFound(email)
        return getCoreUser(coreUserId)
    }

    private fun findIdByEmail(email: Email): CoreUserId? {
        return emailCache.get(email) {
            coreUserDao.findIdByEmail(it)
        }
    }
}