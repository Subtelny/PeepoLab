package pl.peepolab.integration.slack.application.integration

import pl.peepolab.module.api.integration.strategy.ExternalIntegrationAuthStrategy

sealed interface ExternalUserIntegrationStatus {
    object Integrated : ExternalUserIntegrationStatus
    data class Unauthenticated(val authStrategy: ExternalIntegrationAuthStrategy) : ExternalUserIntegrationStatus
    data class NotIntegrated(val authStrategy: ExternalIntegrationAuthStrategy) : ExternalUserIntegrationStatus

}