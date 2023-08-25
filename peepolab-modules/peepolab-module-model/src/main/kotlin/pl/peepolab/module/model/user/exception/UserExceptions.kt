package pl.peepolab.module.model.user.exception

import pl.peepolab.module.model.user.model.UserId


sealed class UserException(message: String) : RuntimeException(message) {

    class NotFound(userId: UserId) : UserException("User with id $userId not found")

    class AlreadyExists(userId: UserId) : UserException("User with id $userId already exists")

}
