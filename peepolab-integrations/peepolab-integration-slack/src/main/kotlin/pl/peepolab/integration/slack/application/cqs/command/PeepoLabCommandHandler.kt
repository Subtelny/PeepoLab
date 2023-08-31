package pl.peepolab.integration.slack.application.cqs.command

import com.slack.api.bolt.context.builtin.SlashCommandContext
import com.slack.api.bolt.request.builtin.SlashCommandRequest
import com.slack.api.bolt.response.Response
import com.slack.api.bolt.socket_mode.SocketModeApp
import com.slack.api.methods.request.users.UsersListRequest
import jakarta.inject.Singleton

private const val COMMAND_NAME = "/plab"

@Singleton
class PeepoLabCommandHandler(
    private val socketModeApp: SocketModeApp,
) : SlackSlashCommandHandler {
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

        val result = context.client().usersList(UsersListRequest.builder().build())
        result.members.forEach {
            println(it.name + " " + it.realName + " " + it.profile.email + " " + it.id)
        }
        println(result.members.size)

        val confa = context.client().conversationsOpen {
            it.users(listOf("U05P1QYVC83"))
        }
        context.client().chatPostMessage {
            it.channel(confa.channel.id)
                .text("Hello world!")
        }
        return Response.ok()
    }
}