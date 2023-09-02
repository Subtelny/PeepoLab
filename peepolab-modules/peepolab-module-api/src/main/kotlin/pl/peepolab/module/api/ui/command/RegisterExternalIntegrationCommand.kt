package pl.peepolab.module.api.ui.command

import pl.peepolab.module.api.integration.ExternalIntegration

data class RegisterExternalIntegrationCommand(val externalIntegration: ExternalIntegration) : CoreCommand