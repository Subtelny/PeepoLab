package pl.peepolab.module.model.user.exception

import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.datatype.Email


sealed class UserException(message: String) : RuntimeException(message) {

    class NotFound : UserException {
        constructor(userId: CoreUserId) : super("User with id $userId not found")
        constructor(email: Email) : super("User with email $email not found")
    }

    class AlreadyExists(email: Email) : UserException("User with email $email already exists")

}
