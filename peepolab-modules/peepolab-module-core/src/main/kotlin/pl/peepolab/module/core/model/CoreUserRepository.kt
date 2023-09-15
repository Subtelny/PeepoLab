package pl.peepolab.module.core.model

import pl.peepolab.module.model.user.model.CoreUser
import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.datatype.Email

interface CoreUserRepository {

    fun createCoreUser(email: Email): CoreUser

    fun getCoreUser(coreUserId: CoreUserId): CoreUser

    fun getCoreUser(email: Email): CoreUser

}