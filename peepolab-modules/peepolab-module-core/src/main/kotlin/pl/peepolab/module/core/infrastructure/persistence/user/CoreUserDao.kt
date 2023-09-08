package pl.peepolab.module.core.infrastructure.persistence.user

import org.jooq.generated.Tables
import org.slf4j.LoggerFactory
import pl.peepolab.module.api.infrastructure.ConnectionProvider
import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.dao.Dao

class CoreUserDao(
    private val connectionProvider: ConnectionProvider,
) : Dao<CoreUserId, CoreUserEntity> {

    override fun find(id: CoreUserId): CoreUserEntity? = with(connectionProvider.getConnection()) {
        selectFrom(Tables.CORE_USER)
            .where(Tables.CORE_USER.ID.eq(id.value))
            .fetchOne {
                CoreUserEntity(
                    it.id,
                    it.email,
                )
            }
    }

    override fun save(model: CoreUserEntity) {
        with(connectionProvider.getConnection()) {
            insertInto(Tables.CORE_USER)
                .set(Tables.CORE_USER.ID, model.id)
                .set(Tables.CORE_USER.EMAIL, model.email)
                .onDuplicateKeyUpdate()
                .set(Tables.CORE_USER.EMAIL, model.email)
                .execute()
        }
    }

    override fun delete(model: CoreUserEntity) {
        with(connectionProvider.getConnection()) {
            deleteFrom(Tables.CORE_USER)
                .where(Tables.CORE_USER.ID.eq(model.id))
                .execute()
        }
    }

}