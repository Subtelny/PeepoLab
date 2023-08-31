package pl.peepolab.module.model.user.model

import pl.peepolab.module.model.integration.UserIntegrationType
import pl.peepolab.utilities.datatype.Identity

class ExternalUserId(
    val type: UserIntegrationType,
    val userId: String
) : Identity<String>(type.type + "@" + userId) {

    companion object {
        fun of(value: String) =
            ExternalUserId(UserIntegrationType(value.substringBefore("@")), value.substringAfter("@"))
    }

}