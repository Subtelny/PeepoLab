package pl.peepolab.module.core.application

import pl.peepolab.module.model.user.model.User
import pl.peepolab.module.model.user.model.UserId

interface UserDao {

    fun get(userId: UserId): User = find(userId) ?: throw IllegalStateException("User not found: $userId")

    fun find(userId: UserId): User?

    fun save(user: User)

}