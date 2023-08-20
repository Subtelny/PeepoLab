package pl.peepolab.core.domain.user.exception

import pl.peepolab.core.domain.integration.model.IntegrationType
import pl.peepolab.core.domain.user.model.UserId


sealed class UserException(message: String) : RuntimeException(message) {

    class NotFound(userId: UserId) : UserException("User with id $userId not found")
    class AlreadyIntegrated(userId: UserId, integrationType: IntegrationType) :
        UserException("User with id $userId already has integration $integrationType")

    class UserIntegrationFailed(userId: UserId, integrationType: IntegrationType) :
        UserException("Could not associate integration $integrationType with user $userId")

}
