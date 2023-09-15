package pl.peepolab.module.api.ui.query

import pl.peepolab.module.api.integration.strategy.ExternalIntegrationAuthStrategy
import pl.peepolab.module.model.integration.ExternalIntegrationType
import pl.peepolab.module.model.user.model.CoreUserId

data class GetExternalIntegrationAuthStrategyQuery(
    val userId: CoreUserId,
    val userIntegrationType: ExternalIntegrationType,
) : CoreQuery<ExternalIntegrationAuthStrategy>