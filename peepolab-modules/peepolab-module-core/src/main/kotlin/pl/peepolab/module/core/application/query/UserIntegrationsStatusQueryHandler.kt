package pl.peepolab.module.core.application.query

import jakarta.inject.Singleton
import pl.peepolab.module.api.ui.query.GetUserIntegrationsStatusQuery
import pl.peepolab.module.api.ui.query.GetUserIntegrationsStatusResult
import pl.peepolab.module.core.application.UserIntegrationsService
import pl.peepolab.module.core.application.UserService
import pl.peepolab.module.core.application.cqs.CoreQueryHandler

@Singleton
class UserIntegrationsStatusQueryHandler(
    private val userIntegrationsService: UserIntegrationsService,
    private val userService: UserService,
) : CoreQueryHandler<GetUserIntegrationsStatusQuery, GetUserIntegrationsStatusResult> {
    override fun handle(query: GetUserIntegrationsStatusQuery): GetUserIntegrationsStatusResult {
        val userId = userService.getUserId(query.externalUserId)
        val integrationsStatuses = userIntegrationsService.getUserIntegrationsStatuses(userId, query.filter)
        return GetUserIntegrationsStatusResult(integrationsStatuses)
    }
}