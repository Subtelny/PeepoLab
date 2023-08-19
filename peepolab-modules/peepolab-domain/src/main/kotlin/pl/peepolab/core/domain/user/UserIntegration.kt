package pl.peepolab.core.domain.user

import pl.peepolab.core.domain.integration.IntegrationType

abstract class UserIntegration(
    val integrationType: IntegrationType,
)