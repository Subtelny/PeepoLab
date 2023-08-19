package pl.peepolab.core.domain.user

interface Users {

    fun getUser(userId: UserId): User {
        return findUser(userId) ?: throw UserNotFoundException(userId)
    }

    fun findUser(userId: UserId): User?
    fun saveUser(user: User)

}