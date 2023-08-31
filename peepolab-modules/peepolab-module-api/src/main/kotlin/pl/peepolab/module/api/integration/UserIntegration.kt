package pl.peepolab.module.api.integration

import pl.peepolab.module.model.integration.UserIntegrationStatus
import pl.peepolab.module.model.integration.UserIntegrationType
import pl.peepolab.module.model.user.model.UserId

interface UserIntegration {

    fun getUserIntegrationStatus(userId: UserId): UserIntegrationStatus
    fun getType(): UserIntegrationType

}