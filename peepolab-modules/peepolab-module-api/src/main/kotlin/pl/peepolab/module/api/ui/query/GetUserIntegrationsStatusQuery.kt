package pl.peepolab.module.api.ui.query

import pl.peepolab.module.model.integration.UserIntegrationStatus
import pl.peepolab.module.model.integration.UserIntegrationType
import pl.peepolab.module.model.user.model.ExternalUserId

data class GetUserIntegrationsStatusQuery(
    val externalUserId: ExternalUserId,
    val filter: (type: UserIntegrationType) -> Boolean = { true },
) : CoreQuery<GetUserIntegrationsStatusResult>

data class GetUserIntegrationsStatusResult(
    val integrations: Map<UserIntegrationType, UserIntegrationStatus>
)