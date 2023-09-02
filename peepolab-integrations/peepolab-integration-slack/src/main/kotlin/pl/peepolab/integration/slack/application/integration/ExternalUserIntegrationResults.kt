package pl.peepolab.integration.slack.application.integration

import pl.peepolab.module.api.integration.dto.ExternalIntegrationAuthStrategyDTO

sealed interface ExternalUserIntegrationStatus {
    object Integrated : ExternalUserIntegrationStatus
    data class Unauthenticated(val authStrategy: ExternalIntegrationAuthStrategyDTO) : ExternalUserIntegrationStatus
    data class NotIntegrated(val authStrategy: ExternalIntegrationAuthStrategyDTO) : ExternalUserIntegrationStatus

}