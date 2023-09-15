package pl.peepolab.integration.gitlab.infrastructure.persistence.dao

import org.jooq.generated.Tables
import pl.peepolab.integration.gitlab.model.GitlabUserId
import pl.peepolab.module.api.infrastructure.ConnectionProvider
import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.dao.Dao

class GitlabUserDao(
    private val connectionProvider: ConnectionProvider,
) : Dao<GitlabUserId, GitlabUserEntity> {
    override fun find(id: GitlabUserId): GitlabUserEntity? = with(connectionProvider.getConnection()) {
        selectFrom(Tables.GITLAB_USER)
            .where(Tables.GITLAB_USER.ID.eq(id.value))
            .fetchOne()
            ?.into(GitlabUserEntity::class.java)
    }

    override fun save(model: GitlabUserEntity) {
        with(connectionProvider.getConnection()) {
            insertInto(Tables.GITLAB_USER)
                .set(Tables.GITLAB_USER.ID, model.id)
                .set(Tables.GITLAB_USER.CORE_USER_ID, model.coreUserId)
                .set(Tables.GITLAB_USER.EMAIL, model.email)
                .set(Tables.GITLAB_USER.USERNAME, model.username)
                .onDuplicateKeyUpdate()
                .set(Tables.GITLAB_USER.EMAIL, model.email)
                .set(Tables.GITLAB_USER.USERNAME, model.username)
                .execute()
        }
    }

    override fun delete(model: GitlabUserEntity) {
        with(connectionProvider.getConnection()) {
            deleteFrom(Tables.GITLAB_USER)
                .where(Tables.GITLAB_USER.ID.eq(model.id))
                .execute()
        }
    }

    fun findByCoreUserId(coreUserId: CoreUserId): GitlabUserEntity? = with(connectionProvider.getConnection()) {
        selectFrom(Tables.GITLAB_USER)
            .where(Tables.GITLAB_USER.CORE_USER_ID.eq(coreUserId.value))
            .fetchOne()
            ?.into(GitlabUserEntity::class.java)
    }
}