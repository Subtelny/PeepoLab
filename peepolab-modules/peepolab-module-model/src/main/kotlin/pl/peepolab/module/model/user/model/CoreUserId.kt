package pl.peepolab.module.model.user.model

import pl.peepolab.utilities.datatype.Identity
import java.util.UUID

class CoreUserId(value: UUID) : Identity<UUID>(value) {

    companion object {
        fun of(value: UUID): CoreUserId {
            return CoreUserId(value)
        }
    }

}