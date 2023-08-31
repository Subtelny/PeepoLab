package pl.peepolab.module.api.ui.query

import pl.peepolab.module.api.integration.dto.IntegrationAuthStrategyDTO
import pl.peepolab.module.model.integration.UserIntegrationType
import pl.peepolab.module.model.user.model.ExternalUserId

data class GetUserIntegrationStrategyQuery(
    val externalUserId: ExternalUserId,
    val userIntegrationType: UserIntegrationType,
) : CoreQuery<IntegrationAuthStrategyDTO>