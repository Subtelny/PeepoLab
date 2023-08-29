package pl.peepolab.integration.slack.application.service

import jakarta.inject.Singleton
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.module.api.integration.dto.AvailableIntegrationDTO
import pl.peepolab.module.api.integration.dto.IntegrationType

@Singleton
class SlackAvailableIntegrationsImpl : SlackAvailableIntegrations {
    override fun findAvailableIntegration(
        userId: SlackUserId,
        integrationType: IntegrationType
    ): AvailableIntegrationDTO? {
        TODO("Not yet implemented")
    }
}