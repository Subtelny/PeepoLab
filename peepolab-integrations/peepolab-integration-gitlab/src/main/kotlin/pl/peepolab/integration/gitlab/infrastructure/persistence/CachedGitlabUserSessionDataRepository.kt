package pl.peepolab.integration.gitlab.infrastructure.persistence

import com.github.benmanes.caffeine.cache.Caffeine
import jakarta.inject.Singleton
import pl.peepolab.integration.gitlab.infrastructure.persistence.dao.GitlabUserSessionDataDao
import pl.peepolab.integration.gitlab.infrastructure.persistence.dao.toEntity
import pl.peepolab.integration.gitlab.infrastructure.persistence.dao.toModel
import pl.peepolab.integration.gitlab.infrastructure.session.model.GitlabUserSessionData
import pl.peepolab.integration.gitlab.model.GitlabUserId
import pl.peepolab.module.api.infrastructure.ConnectionProvider
import java.util.concurrent.TimeUnit

@Singleton
class CachedGitlabUserSessionDataRepository(
    connectionProvider: ConnectionProvider,
) {

    private val cache = Caffeine.newBuilder()
        .expireAfterAccess(1, TimeUnit.HOURS)
        .build<GitlabUserId, GitlabUserSessionData>()

    private val dao: GitlabUserSessionDataDao = GitlabUserSessionDataDao(connectionProvider)

    fun findSessionData(gitlabUserId: GitlabUserId): GitlabUserSessionData? {
        return cache.get(gitlabUserId) {
            dao.find(it)?.toModel()
        }
    }

    fun saveSessionData(gitlabUserSessionData: GitlabUserSessionData) {
        dao.save(gitlabUserSessionData.toEntity())
        cache.put(gitlabUserSessionData.gitlabUserId, gitlabUserSessionData)
    }

}