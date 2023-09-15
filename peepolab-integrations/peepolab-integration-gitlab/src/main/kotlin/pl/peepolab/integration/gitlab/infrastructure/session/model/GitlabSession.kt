package pl.peepolab.integration.gitlab.infrastructure.session.model

import org.gitlab4j.api.GitLabApi
import java.time.Instant

private const val FIVE_MINUTES: Long = 60 * 5

class GitlabSession(
    val gitlabApi: GitLabApi,
    private val expireAt: Instant,
) {
    fun needsRefresh(): Boolean {
        val now = Instant.now()
        val expireAtMinusOffset = expireAt.minusSeconds(FIVE_MINUTES)
        return now.isAfter(expireAtMinusOffset)
    }

}