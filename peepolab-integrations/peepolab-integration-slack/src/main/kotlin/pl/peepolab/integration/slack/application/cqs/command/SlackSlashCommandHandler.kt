package pl.peepolab.integration.slack.application.cqs.command

import com.slack.api.bolt.handler.builtin.SlashCommandHandler

interface SlackSlashCommandHandler : SlashCommandHandler {

    fun getCommand(): String

}