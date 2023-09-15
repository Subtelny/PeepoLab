package pl.peepolab.integration.slack.infrastructure.integration

import pl.peepolab.module.api.integration.ExternalIntegration
import pl.peepolab.module.api.integration.strategy.ExternalIntegrationAuthStrategy
import pl.peepolab.module.model.integration.ExternalIntegrationType
import pl.peepolab.module.model.integration.ExternalUserIntegrationStatus
import pl.peepolab.module.model.user.model.CoreUserId

class SlackExternalIntegration : ExternalIntegration.Messaging {
    override fun getExternalUserIntegrationStatus(coreUserId: CoreUserId): ExternalUserIntegrationStatus {
        throw UnsupportedOperationException("Not necessary for Slack integration")
    }

    override fun getExternalIntegrationAuthStrategy(coreUserId: CoreUserId): ExternalIntegrationAuthStrategy {
        throw UnsupportedOperationException("Not necessary for Slack integration")
    }

    override fun getType(): ExternalIntegrationType =
        ExternalIntegrationType("SLACK")
}