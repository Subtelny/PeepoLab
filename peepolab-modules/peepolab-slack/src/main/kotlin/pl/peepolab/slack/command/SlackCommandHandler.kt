package pl.peepolab.slack.command

import com.slack.api.bolt.handler.builtin.SlashCommandHandler

interface SlackCommandHandler : SlashCommandHandler {

    fun getCommand(): String

}