package pl.peepolab.module.api.integration.dto

sealed class IntegrationAuthStrategy {
    data class OpenId(val url: String) : IntegrationAuthStrategy()

}