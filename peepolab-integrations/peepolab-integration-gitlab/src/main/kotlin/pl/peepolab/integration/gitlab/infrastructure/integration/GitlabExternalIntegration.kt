package pl.peepolab.integration.gitlab.infrastructure.integration

import pl.peepolab.module.api.integration.ExternalIntegration
import pl.peepolab.module.model.integration.UserExternalIntegrationStatus
import pl.peepolab.module.model.integration.ExternalIntegrationType
import pl.peepolab.module.model.user.model.CoreUserId

class GitlabExternalIntegration : ExternalIntegration.VersionControl {

    override fun getUserMergeRequests(userId: CoreUserId) {
        TODO("Not yet implemented")
    }

    override fun getUserInformation(userId: CoreUserId): UserExternalIntegrationStatus {
        TODO("Not yet implemented")
    }

    override fun getType(): ExternalIntegrationType = ExternalIntegrationType("GITLAB")

}