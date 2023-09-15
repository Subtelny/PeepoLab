package pl.peepolab.module.core.application.cqs.query

import jakarta.inject.Singleton
import pl.peepolab.module.api.integration.strategy.ExternalIntegrationAuthStrategy
import pl.peepolab.module.api.ui.query.GetExternalIntegrationAuthStrategyQuery
import pl.peepolab.module.core.application.UserExternalIntegrationsService
import pl.peepolab.module.core.application.cqs.CoreQueryHandler

@Singleton
class GetExternalIntegrationAuthStrategyQueryHandler(
    private val userExternalIntegrationsService: UserExternalIntegrationsService,
) :
    CoreQueryHandler<GetExternalIntegrationAuthStrategyQuery, ExternalIntegrationAuthStrategy> {

    override fun handle(query: GetExternalIntegrationAuthStrategyQuery): ExternalIntegrationAuthStrategy {
        val integration = userExternalIntegrationsService.getIntegration(query.userIntegrationType)
        return integration.getExternalIntegrationAuthStrategy(query.userId)
    }

}