package pl.peepolab.module.core.infrastructure

import pl.peepolab.module.api.CoreContext
import pl.peepolab.module.api.integration.CoreContextExternalIntegrations
import pl.peepolab.module.api.integration.ExternalIntegration
import pl.peepolab.module.core.application.UserExternalIntegrationsService

class AppCoreContext(
    private val userExternalIntegrationsService: UserExternalIntegrationsService,
) : CoreContext {

    private val coreIntegrations = object : CoreContextExternalIntegrations {
        override fun registerIntegration(externalIntegration: ExternalIntegration) {
            userExternalIntegrationsService.registerIntegration(externalIntegration)
        }
    }

    override fun getCoreIntegrations(): CoreContextExternalIntegrations = coreIntegrations

}