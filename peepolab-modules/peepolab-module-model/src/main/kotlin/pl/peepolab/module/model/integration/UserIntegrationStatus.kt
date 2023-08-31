package pl.peepolab.module.model.integration

/***
 * isAuthorized could be true when user don't need to authorize integration
 * (e.g. when user is integrated with Slack and Slack integration is enabled)
 */
data class UserIntegrationStatus(
    val isIntegrated: Boolean,
    val isAuthorized: Boolean,
)
