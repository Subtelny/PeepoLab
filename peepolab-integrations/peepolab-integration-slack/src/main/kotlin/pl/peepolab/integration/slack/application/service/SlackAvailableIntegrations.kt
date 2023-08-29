package pl.peepolab.integration.slack.application.service

import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.module.api.integration.dto.AvailableIntegrationDTO
import pl.peepolab.module.api.integration.dto.IntegrationType

interface SlackAvailableIntegrations {

    fun findAvailableIntegration(userId: SlackUserId, integrationType: IntegrationType): AvailableIntegrationDTO?

}