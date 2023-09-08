package pl.peepolab.module.model.user.model

import pl.peepolab.utilities.datatype.Email

data class CoreUser(
    val id: CoreUserId,
    val email: Email,
)