package pl.peepolab.integration.slack.model

import pl.peepolab.module.api.integration.dto.IntegrationType

object SlackIntegrationType : IntegrationType("SLACK")

fun IntegrationType.isSlack(): Boolean {
    return type == SlackIntegrationType.type
}