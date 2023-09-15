package pl.peepolab.module.model.integration

/***
 * isAuthorized could be true when user don't need to authorize integration
 * (e.g. when user is integrated with Slack and Slack integration is enabled)
 */
sealed class ExternalUserIntegrationStatus private constructor(
    val isIntegrated: Boolean,
    val isAuthorized: Boolean,
) {

    object NotIntegrated : ExternalUserIntegrationStatus(
        isIntegrated = false,
        isAuthorized = false,
    )

    object IntegratedAndAuthorized : ExternalUserIntegrationStatus(
        isIntegrated = true,
        isAuthorized = true,
    )

    object IntegratedNotAuthorized : ExternalUserIntegrationStatus(
        isIntegrated = true,
        isAuthorized = false,
    )

}

