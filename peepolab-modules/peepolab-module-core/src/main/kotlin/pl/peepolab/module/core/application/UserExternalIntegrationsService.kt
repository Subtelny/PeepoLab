package pl.peepolab.module.core.application

import jakarta.inject.Singleton
import pl.peepolab.module.api.integration.ExternalIntegration
import pl.peepolab.module.model.integration.ExternalIntegrationType

@Singleton
class UserExternalIntegrationsService {

    private val integrations: MutableMap<ExternalIntegrationType, ExternalIntegration> = mutableMapOf()

    fun registerIntegration(integration: ExternalIntegration) {
        require(!integrations.containsKey(integration.getType())) { "Integration ${integration.getType()} already registered" }
        integrations[integration.getType()] = integration
    }

    fun getIntegration(type: ExternalIntegrationType): ExternalIntegration {
        return integrations[type] ?: error("Integration $type not registered")
    }

}