package pl.peepolab.integration.slack.model.exception

sealed class SlackUserException(message: String) : RuntimeException(message) {

    class NotFound(val slackUserId: String) : SlackUserException("Slack user with id $slackUserId not found")

}