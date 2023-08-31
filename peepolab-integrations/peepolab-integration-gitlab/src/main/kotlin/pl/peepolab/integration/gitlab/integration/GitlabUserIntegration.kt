package pl.peepolab.integration.gitlab.integration

import pl.peepolab.module.api.integration.UserIntegration
import pl.peepolab.module.model.integration.UserIntegrationType
import pl.peepolab.module.model.user.model.UserId

class GitlabUserIntegration : UserIntegration {

    override fun isUserIntegrated(userId: UserId): Boolean {
        TODO("Not yet implemented")
    }

    override fun getType(): UserIntegrationType = GitlabIntegrationType

}