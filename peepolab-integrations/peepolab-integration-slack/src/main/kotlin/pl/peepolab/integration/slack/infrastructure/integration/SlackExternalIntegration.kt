package pl.peepolab.integration.slack.infrastructure.integration

import pl.peepolab.module.api.integration.ExternalIntegration
import pl.peepolab.module.model.integration.ExternalIntegrationType
import pl.peepolab.module.model.integration.UserExternalIntegrationStatus
import pl.peepolab.module.model.user.model.CoreUserId

class SlackExternalIntegration : ExternalIntegration.Messaging {
    override fun sendMessage(userId: CoreUserId, message: String) {
        TODO("Not yet implemented")
    }

    override fun getUserInformation(userId: CoreUserId): UserExternalIntegrationStatus {
        TODO("Not yet implemented")
    }

    override fun getType(): ExternalIntegrationType =
        ExternalIntegrationType("SLACK")
}