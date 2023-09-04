package pl.peepolab.module.model.user.model

import pl.peepolab.utilities.datatype.Identity
import java.util.UUID

class UserId(value: UUID) : Identity<UUID>(value) {

    companion object {
        fun of(value: UUID): UserId {
            return UserId(value)
        }
    }

}