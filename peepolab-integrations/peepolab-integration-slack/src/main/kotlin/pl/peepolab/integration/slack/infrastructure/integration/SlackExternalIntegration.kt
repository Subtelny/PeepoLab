package pl.peepolab.integration.slack.infrastructure.integration

import pl.peepolab.module.api.integration.ExternalIntegration
import pl.peepolab.module.model.integration.ExternalIntegrationType
import pl.peepolab.module.model.integration.UserExternalIntegrationStatus
import pl.peepolab.module.model.user.model.UserId

class SlackExternalIntegration : ExternalIntegration.Messaging {
    override fun sendMessage(userId: UserId, message: String) {
        TODO("Not yet implemented")
    }

    override fun getUserInformation(userId: UserId): UserExternalIntegrationStatus {
        TODO("Not yet implemented")
    }

    override fun getType(): ExternalIntegrationType =
        ExternalIntegrationType("SLACK")
}