package pl.peepolab.module.api.ui.query

import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.datatype.Email


sealed interface GetUserInformationQuery : CoreQuery<UserInformationResult> {

    class ByEmail(val email: Email) : GetUserInformationQuery

}

data class UserInformationResult(
    val userId: CoreUserId,
)