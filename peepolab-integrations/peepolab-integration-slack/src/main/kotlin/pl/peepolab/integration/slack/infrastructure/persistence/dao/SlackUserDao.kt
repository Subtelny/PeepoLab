package pl.peepolab.integration.slack.infrastructure.persistence.dao

import org.jooq.generated.Tables
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.module.api.infrastructure.ConnectionProvider
import pl.peepolab.utilities.dao.Dao

internal class SlackUserDao(
    private val connectionProvider: ConnectionProvider,
) : Dao<SlackUserId, SlackUserEntity> {

    override fun find(id: SlackUserId): SlackUserEntity? =
        with(connectionProvider.getConnection()) {
            selectFrom(Tables.SLACK_USER)
                .where(Tables.SLACK_USER.ID.eq(id.value))
                .fetchOne {
                    SlackUserEntity(
                        it.coreUserId,
                        it.id,
                        it.email,
                        it.name,
                        it.realName,
                        it.avatar,
                    )
                }

        }

    override fun save(model: SlackUserEntity) {
        with(connectionProvider.getConnection()) {
            insertInto(Tables.SLACK_USER)
                .set(Tables.SLACK_USER.EMAIL, model.email)
                .set(Tables.SLACK_USER.ID, model.slackUserId)
                .set(Tables.SLACK_USER.CORE_USER_ID, model.coreUserId)
                .set(Tables.SLACK_USER.NAME, model.name)
                .set(Tables.SLACK_USER.REAL_NAME, model.realName)
                .set(Tables.SLACK_USER.AVATAR, model.avatar)
                .onDuplicateKeyUpdate()
                .set(Tables.SLACK_USER.EMAIL, model.email)
                .set(Tables.SLACK_USER.NAME, model.name)
                .set(Tables.SLACK_USER.REAL_NAME, model.realName)
                .set(Tables.SLACK_USER.AVATAR, model.avatar)
                .execute()
        }
    }

    override fun delete(model: SlackUserEntity) {
        with(connectionProvider.getConnection()) {
            deleteFrom(Tables.SLACK_USER)
                .where(Tables.SLACK_USER.ID.eq(model.slackUserId))
                .execute()
        }
    }
}