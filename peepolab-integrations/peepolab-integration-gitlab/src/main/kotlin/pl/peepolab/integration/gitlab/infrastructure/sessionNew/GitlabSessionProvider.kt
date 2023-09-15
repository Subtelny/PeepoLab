package pl.peepolab.integration.gitlab.infrastructure.sessionNew

import org.jooq.TransactionProvider
import pl.peepolab.integration.gitlab.model.GitlabUserId

interface GitlabSessionProvider {

    fun <T> withSession(gitlabUserId: GitlabUserId, block: (GitlabSession) -> T): TransactionProvider

}