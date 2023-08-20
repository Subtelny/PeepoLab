package pl.peepolab.core.domain.user

import pl.peepolab.core.domain.user.exception.UserException
import pl.peepolab.core.domain.user.model.User
import pl.peepolab.core.domain.user.model.UserId

interface Users {

    fun findUser(userId: UserId): User?

    fun getUser(userId: UserId): User = findUser(userId) ?: throw UserException.NotFound(userId)
    fun saveUser(user: User)

}