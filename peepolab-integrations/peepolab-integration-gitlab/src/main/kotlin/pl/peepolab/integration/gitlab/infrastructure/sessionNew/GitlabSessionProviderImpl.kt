package pl.peepolab.integration.gitlab.infrastructure.sessionNew

import com.github.benmanes.caffeine.cache.Caffeine
import jakarta.inject.Singleton
import pl.peepolab.integration.gitlab.application.AuthorizationCode
import pl.peepolab.integration.gitlab.infrastructure.persistence.CachedGitlabUserSessionDataRepository
import pl.peepolab.integration.gitlab.model.GitlabUserId
import java.util.concurrent.TimeUnit

@Singleton
class GitlabSessionProviderImpl(
    private val gitlabUserSessionDataRepository: CachedGitlabUserSessionDataRepository,
) : GitlabSessionProvider {

    private val cache = Caffeine.newBuilder()
        .expireAfterAccess(1, TimeUnit.HOURS)
        .build<GitlabUserId, GitlabSession>()

    override fun <T> withSession(gitlabUserId: GitlabUserId, block: (GitlabSession) -> T): T {
        TODO("Not yet implemented")
    }

}