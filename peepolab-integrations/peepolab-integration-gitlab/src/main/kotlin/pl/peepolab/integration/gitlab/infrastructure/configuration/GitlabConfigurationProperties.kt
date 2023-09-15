package pl.peepolab.integration.gitlab.infrastructure.configuration

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties(value = "gitlab.configuration", cliPrefix = [""])
data class GitlabConfigurationProperties(
    val clientId: String,
    val clientSecret: String,
    val scopes: String,
    val gitlabUrl: String,
    val redirectUrl: String,
)