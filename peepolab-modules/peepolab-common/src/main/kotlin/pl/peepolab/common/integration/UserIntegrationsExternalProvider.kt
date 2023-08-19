package pl.peepolab.common.integration

import pl.peepolab.utilities.datatype.Email
import pl.peepolab.utilities.datatype.Identity

interface UserIntegrationsExternalProvider {

    fun findUserIntegration(request: UserIntegrationExternalRequest): UserIntegrationExternalResult

    fun getType(): ExternalIntegrationType

}

data class UserIntegrationExternalRequest(
    val userId: Identity<Long>,
    val email: Email?,
    val userName: String?,
)

data class UserIntegrationExternalResult(
    val data: UserIntegrationExternalData?,
)

data class UserIntegrationExternalData(
    val dupa: String,
)

data class UserIntegrationExternalType(val type: String)