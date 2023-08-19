package pl.peepolab.core.domain.user

import pl.peepolab.core.domain.integration.IntegrationUserId

class User(
    val userId: UserId,
    val userIntegrations: List<IntegrationUserId>,
) {
    fun addUserIntegration(integrationUserId: IntegrationUserId) {
        userIntegrations.plus(integrationUserId)
        //throw event
    }


}