package pl.peepolab.module.api.ui.query

import pl.peepolab.module.api.integration.dto.ExternalIntegrationAuthStrategyDTO
import pl.peepolab.module.model.integration.ExternalIntegrationType
import pl.peepolab.module.model.user.model.CoreUserId

data class GetUserExternalIntegrationStrategyQuery(
    val userId: CoreUserId,
    val userIntegrationType: ExternalIntegrationType,
) : CoreQuery<ExternalIntegrationAuthStrategyDTO>