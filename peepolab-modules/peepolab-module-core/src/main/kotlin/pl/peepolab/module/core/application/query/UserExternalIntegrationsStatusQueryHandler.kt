package pl.peepolab.module.core.application.query

import jakarta.inject.Singleton
import pl.peepolab.module.api.ui.query.GetUserExternalIntegrationStatusQuery
import pl.peepolab.module.core.application.UserExternalIntegrationsService
import pl.peepolab.module.core.application.cqs.CoreQueryHandler
import pl.peepolab.module.model.integration.UserExternalIntegrationStatus

@Singleton
class UserExternalIntegrationsStatusQueryHandler(
    private val userExternalIntegrationsService: UserExternalIntegrationsService,
) : CoreQueryHandler<GetUserExternalIntegrationStatusQuery, UserExternalIntegrationStatus> {
    override fun handle(query: GetUserExternalIntegrationStatusQuery): UserExternalIntegrationStatus {
        val integration = userExternalIntegrationsService.getIntegration(query.userIntegrationType)
        return integration.getUserInformation(query.userId)
    }
}