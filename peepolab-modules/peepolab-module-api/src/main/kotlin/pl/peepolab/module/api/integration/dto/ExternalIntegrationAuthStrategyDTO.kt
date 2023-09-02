package pl.peepolab.module.api.integration.dto

/***
 * Do dodania `codeChallenge` i `codeChallengeMethod`
 * https://auth0.com/docs/flows/authorization-code-flow-with-proof-key-for-code-exchange-pkce
 */
sealed class ExternalIntegrationAuthStrategyDTO {
    data class OIDC(
        val authorizationEndpoint: String,
        val scope: List<String>,
        val clientId: String,
        val redirectUri: String,
        val state: String,
    ) : ExternalIntegrationAuthStrategyDTO()

}