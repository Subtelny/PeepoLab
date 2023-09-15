package pl.peepolab.module.core.application.cqs.query

import jakarta.inject.Singleton
import pl.peepolab.module.api.ui.query.GetUserExternalIntegrationStatusQuery
import pl.peepolab.module.core.application.UserExternalIntegrationsService
import pl.peepolab.module.core.application.cqs.CoreQueryHandler
import pl.peepolab.module.model.integration.ExternalUserIntegrationStatus

@Singleton
class GetUserExternalIntegrationsStatusQueryHandler(
    private val userExternalIntegrationsService: UserExternalIntegrationsService,
) : CoreQueryHandler<GetUserExternalIntegrationStatusQuery, ExternalUserIntegrationStatus> {
    override fun handle(query: GetUserExternalIntegrationStatusQuery): ExternalUserIntegrationStatus {
        val integration = userExternalIntegrationsService.getIntegration(query.userIntegrationType)
        return integration.getExternalUserIntegrationStatus(query.userId)
    }
}