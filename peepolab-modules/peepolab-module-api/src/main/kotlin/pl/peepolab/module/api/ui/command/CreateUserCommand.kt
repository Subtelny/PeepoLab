package pl.peepolab.module.api.ui.command

import pl.peepolab.module.model.user.model.UserId
import pl.peepolab.utilities.datatype.Email

data class CreateUserCommand(
    val email: Email,
) : CoreCommand<UserId>