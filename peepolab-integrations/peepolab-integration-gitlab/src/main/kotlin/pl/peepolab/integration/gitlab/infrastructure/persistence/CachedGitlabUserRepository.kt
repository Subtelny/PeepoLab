package pl.peepolab.integration.gitlab.infrastructure.persistence

import com.github.benmanes.caffeine.cache.Caffeine
import jakarta.inject.Singleton
import pl.peepolab.integration.gitlab.infrastructure.persistence.dao.GitlabUserDao
import pl.peepolab.integration.gitlab.infrastructure.persistence.dao.GitlabUserEntity
import pl.peepolab.integration.gitlab.infrastructure.persistence.dao.toEntity
import pl.peepolab.integration.gitlab.infrastructure.persistence.dao.toModel
import pl.peepolab.integration.gitlab.model.CreateGitlabUserData
import pl.peepolab.integration.gitlab.model.GitlabUser
import pl.peepolab.integration.gitlab.model.GitlabUserId
import pl.peepolab.integration.gitlab.model.GitlabUserRepository
import pl.peepolab.integration.gitlab.model.exception.GitlabException
import pl.peepolab.module.api.infrastructure.ConnectionProvider
import pl.peepolab.module.model.user.model.CoreUserId
import java.util.concurrent.TimeUnit

@Singleton
class CachedGitlabUserRepository(
    connectionProvider: ConnectionProvider,
) : GitlabUserRepository {

    private val dao = GitlabUserDao(connectionProvider)

    private val cache = Caffeine.newBuilder()
        .expireAfterAccess(1, TimeUnit.HOURS)
        .build<GitlabUserId, GitlabUser>()

    private val cacheCoreUserId = Caffeine.newBuilder()
        .expireAfterAccess(1, TimeUnit.HOURS)
        .build<CoreUserId, GitlabUserId>()

    override fun createGitlabUser(data: CreateGitlabUserData): GitlabUser {
        findGitlabUser(data.gitlabUserId)?.let {
            throw GitlabException.UserAlreadyExists()
        }
        val entity = data.toEntity()
        dao.save(entity)
        cacheCoreUserId.put(data.coreUserId, data.gitlabUserId)
        return getGitlabUser(data.gitlabUserId)
    }

    override fun findGitlabUserByCoreUserId(coreUserId: CoreUserId): GitlabUser? {
        return cacheCoreUserId.get(coreUserId) {
            val model = dao.findByCoreUserId(coreUserId)?.toModel()
            model?.let {
                cache.put(it.id, it)
            }
            model?.id
        }?.let { gitlabUserId ->
            getGitlabUser(gitlabUserId)
        }
    }

    private fun findGitlabUser(gitlabUserId: GitlabUserId): GitlabUser? {
        return cache.get(gitlabUserId) {
            dao.find(gitlabUserId)?.let {
                val model = it.toModel()
                cacheCoreUserId.put(model.coreUserId, model.id)
                model
            }
        }
    }

    private fun getGitlabUser(gitlabUserId: GitlabUserId): GitlabUser {
        return findGitlabUser(gitlabUserId) ?: throw GitlabException.UserNotFound()
    }

}