package pl.peepolab.slack.command

import com.slack.api.bolt.context.builtin.SlashCommandContext
import com.slack.api.bolt.request.builtin.SlashCommandRequest
import com.slack.api.bolt.response.Response
import jakarta.inject.Singleton

private const val COMMAND_NAME = "/plab"

@Singleton
class PeepoLabCommandHandler : SlackCommandHandler {
    override fun getCommand(): String = COMMAND_NAME

    override fun apply(req: SlashCommandRequest, context: SlashCommandContext): Response {
        val botUserId = req.context.botUserId

        val conversationsOpen = context.client().conversationsOpen {
            it.users(listOf(req.payload.userId))
        }

        context.client().chatPostMessage {
            it.channel(conversationsOpen.channel.id)
                .text("Przetwarzam...")

        }
        val usersInfo = context.client().usersInfo { it.user(context.requestUserId) }
        return context.ack("Przetwarzam... Rezultat znajdziesz pod <@$botUserId>")
    }
}