package pl.peepolab.integration.slack.application.integration

import jakarta.inject.Singleton
import pl.peepolab.integration.slack.application.SlackUserService
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.module.api.integration.strategy.ExternalIntegrationAuthStrategy
import pl.peepolab.module.api.ui.CoreService
import pl.peepolab.module.api.ui.query.GetUserExternalIntegrationStatusQuery
import pl.peepolab.module.api.ui.query.GetExternalIntegrationAuthStrategyQuery
import pl.peepolab.module.model.integration.ExternalIntegrationType
import pl.peepolab.module.model.user.model.CoreUserId

@Singleton
class ExternalUserIntegrationsService(
    private val coreService: CoreService,
    private val slackUserService: SlackUserService,
) {

    fun getExternalUserIntegrationInformation(
        slackUserId: SlackUserId,
        integrationType: ExternalIntegrationType,
    ): ExternalUserIntegrationStatus {
        val userId = slackUserService.getSlackUser(slackUserId).coreUserId
        val query = GetUserExternalIntegrationStatusQuery(userId, integrationType)
        val result = coreService.query(query)

        if (result.isIntegrated.not()) {
            val authStrategy = getExternalUserIntegrationAuthStrategy(userId, integrationType)
            return ExternalUserIntegrationStatus.NotIntegrated(authStrategy)
        }
        if (result.isAuthorized.not()) {
            val authStrategy = getExternalUserIntegrationAuthStrategy(userId, integrationType)
            return ExternalUserIntegrationStatus.Unauthenticated(authStrategy)
        }
        return ExternalUserIntegrationStatus.Integrated
    }

    private fun getExternalUserIntegrationAuthStrategy(
        userId: CoreUserId,
        integrationType: ExternalIntegrationType,
    ): ExternalIntegrationAuthStrategy {
        val query = GetExternalIntegrationAuthStrategyQuery(userId, integrationType)
        return coreService.query(query)
    }

}