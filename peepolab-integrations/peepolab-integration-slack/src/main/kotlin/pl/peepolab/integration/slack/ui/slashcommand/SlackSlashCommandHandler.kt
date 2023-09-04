package pl.peepolab.integration.slack.ui.slashcommand

import com.slack.api.bolt.handler.builtin.SlashCommandHandler

interface SlackSlashCommandHandler : SlashCommandHandler {

    fun getCommand(): String

}