package pl.peepolab.module.core.infrastructure.persistence

import jakarta.inject.Singleton
import pl.peepolab.module.core.application.UserDao
import pl.peepolab.module.model.user.model.User
import pl.peepolab.module.model.user.model.UserId


@Singleton
class InMemoryUserDao : UserDao {

    private val users: MutableMap<UserId, User> = mutableMapOf()

    override fun find(userId: UserId): User? = users[userId]

    override fun save(user: User) {
        users[user.userId] = user
    }

}