package pl.peepolab.integration.slack.`interface`.command

import com.slack.api.bolt.handler.builtin.SlashCommandHandler

interface SlackSlashCommandHandler : SlashCommandHandler {

    fun getCommand(): String

}