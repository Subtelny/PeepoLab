package pl.peepolab.integration.slack.model

import pl.peepolab.utilities.datatype.Identity

class SlackUserId(value: String) : Identity<String>(value) {
    companion object {
        fun of(value: String) = SlackUserId(value)
    }
}