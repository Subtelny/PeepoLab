package pl.peepolab.core.domain.integration

import pl.peepolab.core.domain.user.User

interface Integration {

    fun getType(): IntegrationType
    fun associateByUser(user: User): IntegrationUserId?

}