package pl.peepolab.module.api.integration

import pl.peepolab.module.model.integration.UserExternalIntegrationStatus
import pl.peepolab.module.model.integration.ExternalIntegrationType
import pl.peepolab.module.model.user.model.CoreUserId

sealed interface ExternalIntegration {

    fun getUserInformation(userId: CoreUserId): UserExternalIntegrationStatus
    fun getType(): ExternalIntegrationType

    interface Messaging : ExternalIntegration {

        fun sendMessage(userId: CoreUserId, message: String)

    }

    interface VersionControl : ExternalIntegration {

        fun getUserMergeRequests(userId: CoreUserId)

    }

}