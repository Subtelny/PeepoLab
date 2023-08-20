package pl.peepolab.core.domain.integration

import pl.peepolab.core.domain.integration.model.IntegrationType
import pl.peepolab.core.domain.user.model.UserId
import pl.peepolab.core.domain.user.Users
import pl.peepolab.core.domain.user.exception.UserException

class IntegrationService(
    private val integrations: Integrations,
    private val users: Users,
) {

    fun integrateUser(userId: UserId, integrationType: IntegrationType) {
        val user = users.getUser(userId)
        user.integrationUsers.find { it.integrationType == integrationType }
            ?: throw UserException.AlreadyIntegrated(userId, integrationType)

        val integrationUserId = integrations.getIntegration(integrationType)
            .associateByUser(user) ?: throw UserException.UserIntegrationFailed(userId, integrationType)

        user.addIntegrationUser(integrationUserId)
        users.saveUser(user)
    }

}