package pl.peepolab.integration.slack.application.integration

import pl.peepolab.module.api.integration.dto.IntegrationAuthStrategyDTO

sealed interface ExternalUserIntegrationStatus {
    object Integrated : ExternalUserIntegrationStatus
    data class Unauthenticated(val authStrategy: IntegrationAuthStrategyDTO) : ExternalUserIntegrationStatus
    data class NotIntegrated(val authStrategy: IntegrationAuthStrategyDTO) : ExternalUserIntegrationStatus

}