package pl.peepolab.integration.slack.command

import com.slack.api.Slack
import com.slack.api.bolt.App
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
) : SlackCommandHandler {
    override fun getCommand(): String = COMMAND_NAME

    private var lastMessage: String? = null

    private var token: String? = null

    override fun apply(req: SlashCommandRequest, context: SlashCommandContext): Response {
        val conversationsOpen = context.client().conversationsOpen {
            it.users(listOf(req.payload.userId))
        }

//        if (lastMessage == null) {
//            val result = context.client().chatPostMessage {
//                it.channel(conversationsOpen.channel.id)
//                    .text("Przetwarzam...")
//            }
//            lastMessage = result.ts
//            token = req.payload.token
//        } else {
//            context.client().chatUpdate {
//                it.channel(conversationsOpen.channel.id)
//                    .ts(lastMessage)
//                    .text("Zedytowalem! :)")
//            }
//            lastMessage = null
//        }

        socketModeApp.client.slack.methods(socketModeApp.app.config().singleTeamBotToken, context.teamId).chatPostMessage {
            it.channel(conversationsOpen.channel.id)
                .blocksAsString("[{\"type\":\"section\",\"text\":{\"type\":\"mrkdwn\",\"text\":\"*Zostałeś przypisany jako Reviewer do <fakeLink.toEmployeeProfile.com|Merge Requesta ONEUTRZ-1444>*\"}},{\"type\":\"divider\"},{\"type\":\"section\",\"fields\":[{\"type\":\"mrkdwn\",\"text\":\"*JIRA Issue:*\\n<fakeLink.toEmployeeProfile.com|ONEUTRZ-1444>\"},{\"type\":\"mrkdwn\",\"text\":\"*Opis*\\nCoś tutaj będzie\"},{\"type\":\"mrkdwn\",\"text\":\"*Projekt*\\norder-path\"},{\"type\":\"mrkdwn\",\"text\":\"*Labelki*\\n`WEG`\"}]},{\"type\":\"divider\"},{\"type\":\"context\",\"elements\":[{\"type\":\"mrkdwn\",\"text\":\"*Asignee: *\"},{\"type\":\"image\",\"image_url\":\"https://api.slack.com/img/blocks/bkb_template_images/profile_1.png\",\"alt_text\":\"Michael Scott\"},{\"type\":\"mrkdwn\",\"text\":\"<fakelink.toUser.com|Sebastian Janda>\"}]},{\"type\":\"actions\",\"elements\":[{\"type\":\"button\",\"text\":{\"type\":\"plain_text\",\"text\":\"Przejdź do GitLab\",\"emoji\":true},\"value\":\"click_me_123\",\"url\":\"https://google.com\"},{\"type\":\"button\",\"text\":{\"type\":\"plain_text\",\"text\":\"Wycisz powiadomienia\",\"emoji\":true},\"value\":\"click_me_123\"}]}]")
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