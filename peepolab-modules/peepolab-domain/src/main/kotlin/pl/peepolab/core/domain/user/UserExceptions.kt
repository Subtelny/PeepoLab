package pl.peepolab.core.domain.user

import pl.peepolab.core.domain.integration.IntegrationType

class UserNotFoundException(userId: UserId) : RuntimeException("User with id $userId not found")
class UserAlreadyHasIntegration(userId: UserId, integrationType: IntegrationType) :
    RuntimeException("User with id $userId already has integration $integrationType")

class CouldNotAssociateIntegrationWithUser(userId: UserId, integrationType: IntegrationType) :
    RuntimeException("Could not associate integration $integrationType with user $userId")
