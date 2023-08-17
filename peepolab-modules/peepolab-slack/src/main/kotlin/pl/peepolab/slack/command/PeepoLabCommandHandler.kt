package pl.peepolab.slack.command

import com.slack.api.bolt.context.builtin.SlashCommandContext
import com.slack.api.bolt.request.builtin.SlashCommandRequest
import com.slack.api.bolt.response.Response
import jakarta.inject.Singleton

private const val COMMAND_NAME = "/plab"

@Singleton
class PeepoLabCommandHandler : SlackCommandHandler {
    override fun getCommand(): String = COMMAND_NAME

    private var lastMessage: String? = null

    private var token: String? = null

    override fun apply(req: SlashCommandRequest, context: SlashCommandContext): Response {
        val conversationsOpen = context.client().conversationsOpen {
            it.users(listOf(req.payload.userId))
        }

        if (lastMessage == null) {
            val result = context.client().chatPostMessage {
                it.channel(conversationsOpen.channel.id)
                    .text("Przetwarzam...")
            }
            lastMessage = result.ts
            token = req.payload.token
        } else {
            context.client().chatUpdate {
                it.channel(conversationsOpen.channel.id)
                    .ts(lastMessage)
                    .text("Zedytowalem! :)")
            }
            lastMessage = null
        }

        return Response.ok()
    }
}