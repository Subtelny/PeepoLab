package pl.peepolab.core.domain.integration

import pl.peepolab.core.domain.integration.exception.IntegrationException
import pl.peepolab.core.domain.integration.model.Integration
import pl.peepolab.core.domain.integration.model.IntegrationType

interface Integrations {

    fun findIntegration(integrationType: IntegrationType): Integration?

    fun getIntegration(integrationType: IntegrationType): Integration =
        findIntegration(integrationType) ?: throw IntegrationException.NotFound(integrationType)

}

