package pl.peepolab.module.core.application

import jakarta.inject.Singleton
import pl.peepolab.module.model.user.model.ExternalUserId
import pl.peepolab.module.model.user.model.UserId

@Singleton
class UserService {

    fun getUserId(externalUserId: ExternalUserId): UserId {
        TODO("Not yet implemented")
    }

}