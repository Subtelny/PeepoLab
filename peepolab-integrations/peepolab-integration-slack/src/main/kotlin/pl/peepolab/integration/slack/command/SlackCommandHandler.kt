package pl.peepolab.integration.slack.command

import com.slack.api.bolt.handler.builtin.SlashCommandHandler

interface SlackCommandHandler : SlashCommandHandler {

    fun getCommand(): String

}