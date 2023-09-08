package pl.peepolab.module.api.ui.command

import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.datatype.Email

data class CreateCoreUserCommand(
    val email: Email,
) : CoreCommand<CoreUserId>