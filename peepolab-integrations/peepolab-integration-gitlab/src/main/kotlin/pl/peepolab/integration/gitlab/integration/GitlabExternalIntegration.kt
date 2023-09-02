package pl.peepolab.integration.gitlab.integration

import pl.peepolab.module.api.integration.ExternalIntegration
import pl.peepolab.module.model.integration.UserExternalIntegrationStatus
import pl.peepolab.module.model.integration.ExternalIntegrationType
import pl.peepolab.module.model.user.model.UserId

class GitlabExternalIntegration : ExternalIntegration.VersionControl {

    override fun getUserMergeRequests(userId: UserId) {
        TODO("Not yet implemented")
    }

    override fun getUserInformation(userId: UserId): UserExternalIntegrationStatus {
        TODO("Not yet implemented")
    }

    override fun getType(): ExternalIntegrationType = ExternalIntegrationType("GITLAB")

}