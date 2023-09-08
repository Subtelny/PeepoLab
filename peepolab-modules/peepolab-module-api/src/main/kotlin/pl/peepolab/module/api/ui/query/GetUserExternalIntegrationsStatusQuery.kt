package pl.peepolab.module.api.ui.query

import pl.peepolab.module.model.integration.UserExternalIntegrationStatus
import pl.peepolab.module.model.integration.ExternalIntegrationType
import pl.peepolab.module.model.user.model.CoreUserId

data class GetUserExternalIntegrationStatusQuery(
    val userId: CoreUserId,
    val userIntegrationType: ExternalIntegrationType,
) : CoreQuery<UserExternalIntegrationStatus>