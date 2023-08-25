package pl.peepolab.module.core.infrastructure.persistence

import pl.peepolab.module.core.application.UserDao
import pl.peepolab.module.model.user.model.User
import pl.peepolab.module.model.user.model.UserId

class DatabaseUserDao : UserDao {



    override fun find(userId: UserId): User? {
        TODO("Not yet implemented")
    }

    override fun save(user: User) {
        TODO("Not yet implemented")
    }


}