package pl.peepolab.module.api.integration

import pl.peepolab.module.api.integration.strategy.ExternalIntegrationAuthStrategy
import pl.peepolab.module.model.integration.ExternalUserIntegrationStatus
import pl.peepolab.module.model.integration.ExternalIntegrationType
import pl.peepolab.module.model.user.model.CoreUserId

sealed interface ExternalIntegration {

    fun getExternalUserIntegrationStatus(coreUserId: CoreUserId): ExternalUserIntegrationStatus
    fun getExternalIntegrationAuthStrategy(coreUserId: CoreUserId): ExternalIntegrationAuthStrategy
    fun getType(): ExternalIntegrationType

    interface Messaging : ExternalIntegration

    interface VersionControl : ExternalIntegration

}