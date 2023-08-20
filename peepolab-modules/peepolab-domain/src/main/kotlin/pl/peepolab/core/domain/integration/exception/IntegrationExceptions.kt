package pl.peepolab.core.domain.integration.exception

import pl.peepolab.core.domain.integration.model.IntegrationType

sealed class IntegrationException(message: String) : RuntimeException(message) {
    class NotFound(integrationType: IntegrationType) :
        IntegrationException("Integration $integrationType not found")


}
