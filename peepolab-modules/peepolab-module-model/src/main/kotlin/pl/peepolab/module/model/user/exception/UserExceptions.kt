package pl.peepolab.module.model.user.exception

import pl.peepolab.module.model.user.model.CoreUserId


sealed class UserException(message: String) : RuntimeException(message) {

    class NotFound(userId: CoreUserId) : UserException("User with id $userId not found")

    class AlreadyExists(userId: CoreUserId) : UserException("User with id $userId already exists")

}
