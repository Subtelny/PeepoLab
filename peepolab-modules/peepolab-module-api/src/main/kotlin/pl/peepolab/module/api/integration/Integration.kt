package pl.peepolab.module.api.integration

import pl.peepolab.module.model.user.model.UserId

interface Integration {

    fun isIntegrated(userId: UserId): Boolean
    fun getType(): IntegrationType

}