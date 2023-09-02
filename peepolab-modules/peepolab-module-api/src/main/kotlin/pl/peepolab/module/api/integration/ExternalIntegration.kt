package pl.peepolab.module.api.integration

import pl.peepolab.module.model.integration.UserExternalIntegrationStatus
import pl.peepolab.module.model.integration.ExternalIntegrationType
import pl.peepolab.module.model.user.model.UserId

sealed interface ExternalIntegration {

    fun getUserInformation(userId: UserId): UserExternalIntegrationStatus
    fun getType(): ExternalIntegrationType

    interface Messaging : ExternalIntegration {

        fun sendMessage(userId: UserId, message: String)

    }

    interface VersionControl : ExternalIntegration {

        fun getUserMergeRequests(userId: UserId)

    }

}