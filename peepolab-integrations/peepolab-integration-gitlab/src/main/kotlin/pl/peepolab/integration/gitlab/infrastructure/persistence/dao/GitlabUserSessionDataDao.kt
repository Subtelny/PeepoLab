package pl.peepolab.integration.gitlab.infrastructure.persistence.dao

import org.jooq.generated.Tables
import pl.peepolab.integration.gitlab.model.GitlabUserId
import pl.peepolab.module.api.infrastructure.ConnectionProvider
import pl.peepolab.utilities.dao.Dao

class GitlabUserSessionDataDao(
    private val connectionProvider: ConnectionProvider,
) : Dao<GitlabUserId, GitlabUserSessionDataEntity> {

    override fun find(id: GitlabUserId): GitlabUserSessionDataEntity? = with(connectionProvider.getConnection()) {
        selectFrom(Tables.GITLAB_USER_SESSION_DATA)
            .where(Tables.GITLAB_USER_SESSION_DATA.GITLAB_USER_ID.eq(id.value))
            .fetchOneInto(GitlabUserSessionDataEntity::class.java)
    }

    override fun save(model: GitlabUserSessionDataEntity) {
        with(connectionProvider.getConnection()) {
            insertInto(Tables.GITLAB_USER_SESSION_DATA)
                .set(Tables.GITLAB_USER_SESSION_DATA.GITLAB_USER_ID, model.gitlabUserId)
                .set(Tables.GITLAB_USER_SESSION_DATA.ACCESS_TOKEN, model.accessToken)
                .set(Tables.GITLAB_USER_SESSION_DATA.REFRESH_TOKEN, model.refreshToken)
                .onDuplicateKeyUpdate()
                .set(Tables.GITLAB_USER_SESSION_DATA.ACCESS_TOKEN, model.accessToken)
                .set(Tables.GITLAB_USER_SESSION_DATA.REFRESH_TOKEN, model.refreshToken)
                .execute()
        }
    }

    override fun delete(model: GitlabUserSessionDataEntity) {
        with(connectionProvider.getConnection()) {
            deleteFrom(Tables.GITLAB_USER_SESSION_DATA)
                .where(Tables.GITLAB_USER_SESSION_DATA.GITLAB_USER_ID.eq(model.gitlabUserId))
                .execute()
        }
    }
}