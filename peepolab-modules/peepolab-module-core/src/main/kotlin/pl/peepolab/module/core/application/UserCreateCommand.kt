package pl.peepolab.module.core.application

import pl.peepolab.utilities.datatype.Email

data class UserCreateCommand(
    val email: Email,
    val name: String?,
    val lastname: String?,
    val username: String?,
)