package pl.peepolab.integration.slack.model.exception

import pl.peepolab.integration.slack.model.SlackUserId

sealed class SlackUserException(message: String) : RuntimeException(message) {

    class NotFound(val slackUserId: SlackUserId) : SlackUserException("Slack user with id ${slackUserId.value} not found")
    class AlreadyExists(val slackUserId: SlackUserId) : SlackUserException("Slack user with id ${slackUserId.value} not found")

}