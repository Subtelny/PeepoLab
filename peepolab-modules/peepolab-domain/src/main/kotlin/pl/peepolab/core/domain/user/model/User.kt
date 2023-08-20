package pl.peepolab.core.domain.user.model

import pl.peepolab.core.domain.Entity
import pl.peepolab.core.domain.integration.model.IntegrationUserId
import pl.peepolab.core.domain.user.event.UserIntegratedEvent
import pl.peepolab.core.domain.user.exception.UserException

class User(
    val userId: UserId,
    val integrationUsers: List<IntegrationUserId>,
) : Entity() {
    fun addIntegrationUser(integrationUserId: IntegrationUserId) {
        if (integrationUsers.contains(integrationUserId)) {
            throw UserException.AlreadyIntegrated(userId, integrationUserId.integrationType)
        }
        integrationUsers.plus(integrationUserId)
        addEvent(UserIntegratedEvent(userId, integrationUserId))
    }

}