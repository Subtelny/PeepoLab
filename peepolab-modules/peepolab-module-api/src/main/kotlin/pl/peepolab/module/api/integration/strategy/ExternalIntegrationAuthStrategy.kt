package pl.peepolab.module.api.integration.strategy

/***
 * Do dodania `codeChallenge` i `codeChallengeMethod`
 * https://auth0.com/docs/flows/authorization-code-flow-with-proof-key-for-code-exchange-pkce
 */
sealed class ExternalIntegrationAuthStrategy {
    data class OIDC(
        val authorizationEndpoint: String,
        val scopes: String,
        val clientId: String,
        val redirectUri: String,
        val state: String,
    ) : ExternalIntegrationAuthStrategy()

}