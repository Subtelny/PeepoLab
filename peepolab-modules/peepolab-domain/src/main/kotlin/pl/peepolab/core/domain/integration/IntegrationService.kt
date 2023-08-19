package pl.peepolab.core.domain.integration

import pl.peepolab.core.domain.user.CouldNotAssociateIntegrationWithUser
import pl.peepolab.core.domain.user.UserAlreadyHasIntegration
import pl.peepolab.core.domain.user.UserId
import pl.peepolab.core.domain.user.Users

class IntegrationService(
    private val integrations: Integrations,
    private val users: Users,
) {

    fun integrateUser(userId: UserId, integrationType: IntegrationType) {
        val user = users.getUser(userId)
        user.userIntegrations.find { it.integrationType == integrationType }
            ?: throw UserAlreadyHasIntegration(userId, integrationType)

        val integrationUserId = integrations.getIntegration(integrationType)
            .associateByUser(user) ?: throw CouldNotAssociateIntegrationWithUser(userId, integrationType)

        user.addUserIntegration(integrationUserId)
        users.saveUser(user)
    }

    private fun getAllIntegrations(): List<Integration> {
        return integrations.getAll()
    }

}