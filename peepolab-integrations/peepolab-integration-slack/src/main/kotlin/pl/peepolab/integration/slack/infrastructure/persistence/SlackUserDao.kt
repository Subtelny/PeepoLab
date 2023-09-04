package pl.peepolab.integration.slack.infrastructure.persistence

import org.jooq.generated.Tables
import pl.peepolab.integration.slack.model.SlackUser
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.module.api.infrastructure.ConnectionProvider
import pl.peepolab.module.model.user.model.UserId
import pl.peepolab.utilities.dao.Dao
import pl.peepolab.utilities.datatype.Email

class SlackUserDao(
    private val connectionProvider: ConnectionProvider,
) : Dao<SlackUserId, SlackUser> {

    override fun find(id: SlackUserId): SlackUser? =
        with(connectionProvider.getConnection()) {
            selectFrom(Tables.SLACK_USER)
                .where(Tables.SLACK_USER.SLACKID.eq(id.value))
                .fetchOne {
                    val email = Email(it.email)
                    val slackId = SlackUserId.of(it.slackid)
                    val userId = UserId.of(it.userid)
                    SlackUser(userId, slackId, email)
                }

        }

    override fun save(model: SlackUser) {
        with(connectionProvider.getConnection()) {
            insertInto(Tables.SLACK_USER)
                .set(Tables.SLACK_USER.EMAIL, model.email.value)
                .set(Tables.SLACK_USER.SLACKID, model.slackUserId.value)
                .set(Tables.SLACK_USER.USERID, model.userId.value)
                .onDuplicateKeyUpdate()
                .set(Tables.SLACK_USER.EMAIL, model.email.value)
                .execute()
        }
    }

    override fun delete(model: SlackUser) {
        with(connectionProvider.getConnection()) {
            deleteFrom(Tables.SLACK_USER)
                .where(Tables.SLACK_USER.SLACKID.eq(model.slackUserId.value))
                .execute()
        }
    }
}