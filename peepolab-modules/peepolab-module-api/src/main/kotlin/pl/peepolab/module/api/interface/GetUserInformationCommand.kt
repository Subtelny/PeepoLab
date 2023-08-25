package pl.peepolab.module.api.`interface`

import pl.peepolab.utilities.datatype.Email


sealed interface GetUserInformationCommand {

    class ByEmail(val email: Email) : GetUserInformationCommand
    class ByUsername(val userName: String) : GetUserInformationCommand
    class ByExternalId(val userName: String) : GetUserInformationCommand

}

data class UserInformationResult(
    val userId: Long,
)