package pl.peepolab.core.domain.integration

import pl.peepolab.utilities.datatype.Email
import pl.peepolab.utilities.datatype.Identity

interface Integrations {

    fun findUserIntegrations(request: UserIntegrationRequest): List<Integration>
    fun getAll(): List<Integration>
    fun getIntegration(integrationType: IntegrationType): Integration

}

data class UserIntegrationRequest(
    val userId: Identity<Long>,
    val email: Email?,
    val userName: String?,
)