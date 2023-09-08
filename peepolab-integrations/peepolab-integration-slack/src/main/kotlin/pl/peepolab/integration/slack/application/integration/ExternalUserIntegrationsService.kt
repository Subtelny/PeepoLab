package pl.peepolab.integration.slack.application.integration

import jakarta.inject.Singleton
import pl.peepolab.integration.slack.application.SlackUserService
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.module.api.integration.dto.ExternalIntegrationAuthStrategyDTO
import pl.peepolab.module.api.ui.CoreService
import pl.peepolab.module.api.ui.query.GetUserExternalIntegrationStatusQuery
import pl.peepolab.module.api.ui.query.GetUserExternalIntegrationStrategyQuery
import pl.peepolab.module.model.integration.ExternalIntegrationType
import pl.peepolab.module.model.user.model.CoreUserId

@Singleton
class ExternalUserIntegrationsService(
    private val coreService: CoreService,
    private val slackUserService: SlackUserService,
) {

    fun getUserIntegrationInformation(
        slackUserId: SlackUserId,
        integrationType: ExternalIntegrationType,
    ): ExternalUserIntegrationStatus {
        val userId = slackUserService.getSlackUser(slackUserId).userId
        val query = GetUserExternalIntegrationStatusQuery(userId, integrationType)
        val result = coreService.query(query)

        if (result.isIntegrated.not()) {
            val authStrategy = getUserIntegrationStrategy(userId, integrationType)
            return ExternalUserIntegrationStatus.NotIntegrated(authStrategy)
        }
        if (result.isAuthorized.not()) {
            val authStrategy = getUserIntegrationStrategy(userId, integrationType)
            return ExternalUserIntegrationStatus.Unauthenticated(authStrategy)
        }
        return ExternalUserIntegrationStatus.Integrated
    }

    private fun getUserIntegrationStrategy(
        userId: CoreUserId,
        integrationType: ExternalIntegrationType,
    ): ExternalIntegrationAuthStrategyDTO {
        val query = GetUserExternalIntegrationStrategyQuery(userId, integrationType)
        return coreService.query(query)
    }

}