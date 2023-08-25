package pl.peepolab.integration.gitlab.integration

import pl.peepolab.module.api.integration.IntegrationType
import pl.peepolab.module.api.integration.VersionControlIntegration
import pl.peepolab.module.model.user.model.UserId

class GitlabVersionControlIntegration : VersionControlIntegration {

    override fun isIntegrated(userId: UserId): Boolean {
        TODO("Not yet implemented")
    }

    override fun getType(): IntegrationType = GitlabIntegrationType

}