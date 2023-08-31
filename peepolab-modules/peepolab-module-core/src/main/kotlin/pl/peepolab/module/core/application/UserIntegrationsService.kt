package pl.peepolab.module.core.application

import jakarta.inject.Singleton
import pl.peepolab.module.api.integration.UserIntegration
import pl.peepolab.module.model.integration.UserIntegrationStatus
import pl.peepolab.module.model.integration.UserIntegrationType
import pl.peepolab.module.model.user.model.UserId

@Singleton
class UserIntegrationsService(
    integrations: List<UserIntegration>,
) {

    private val integrationsMap: Map<UserIntegrationType, UserIntegration> = integrations.associateBy { it.getType() }

    fun getUserIntegrationsStatuses(
        userId: UserId,
        filter: (type: UserIntegrationType) -> Boolean = { true },
    ): Map<UserIntegrationType, UserIntegrationStatus> {
        return integrationsMap
            .filterKeys(filter)
            .mapValues { (_, integration) -> integration.getUserIntegrationStatus(userId) }
    }

}