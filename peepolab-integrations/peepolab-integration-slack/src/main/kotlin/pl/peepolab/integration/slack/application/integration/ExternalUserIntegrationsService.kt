package pl.peepolab.integration.slack.application.integration

import jakarta.inject.Singleton
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.module.api.integration.dto.IntegrationAuthStrategyDTO
import pl.peepolab.module.api.ui.CoreService
import pl.peepolab.module.api.ui.query.GetUserIntegrationStrategyQuery
import pl.peepolab.module.api.ui.query.GetUserIntegrationsStatusQuery
import pl.peepolab.module.api.ui.query.GetUserIntegrationsStatusResult
import pl.peepolab.module.model.integration.UserIntegrationType
import pl.peepolab.module.model.user.model.ExternalUserId

@Singleton
class ExternalUserIntegrationsService(
    private val coreService: CoreService,
) {

    fun getUserIntegrationInformation(
        slackUserId: SlackUserId,
        integrationType: UserIntegrationType
    ): ExternalUserIntegrationStatus {
        val query = GetUserIntegrationsStatusQuery(slackUserId.toExternalUserId()) { it == integrationType }
        val result = coreService.query(query)
        val status = result.getIntegrationStatus(integrationType)

        if (status.isIntegrated.not()) {
            val authStrategy = getUserIntegrationStrategy(slackUserId.toExternalUserId(), integrationType)
            return ExternalUserIntegrationStatus.NotIntegrated(authStrategy)
        }
        if (status.isAuthorized.not()) {
            val authStrategy = getUserIntegrationStrategy(slackUserId.toExternalUserId(), integrationType)
            return ExternalUserIntegrationStatus.Unauthenticated(authStrategy)
        }
        return ExternalUserIntegrationStatus.Integrated
    }

    private fun GetUserIntegrationsStatusResult.getIntegrationStatus(integrationType: UserIntegrationType) =
        integrations[integrationType]
            ?: throw IllegalStateException("Integration $integrationType not found")

    private fun getUserIntegrationStrategy(
        externalUserId: ExternalUserId,
        integrationType: UserIntegrationType,
    ): IntegrationAuthStrategyDTO {
        val query = GetUserIntegrationStrategyQuery(externalUserId, integrationType)
        return coreService.query(query)
    }

}