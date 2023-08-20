package pl.peepolab.core.domain.integration.model

import pl.peepolab.core.domain.user.model.User

interface Integration {

    fun getType(): IntegrationType
    fun associateByUser(user: User): IntegrationUserId?

}