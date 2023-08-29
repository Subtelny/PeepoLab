package pl.peepolab.module.api.integration

import pl.peepolab.module.api.integration.dto.IntegrationType
import pl.peepolab.module.model.user.model.UserId

interface Integration {

    fun isUserIntegrated(userId: UserId): Boolean
    fun getType(): IntegrationType

}