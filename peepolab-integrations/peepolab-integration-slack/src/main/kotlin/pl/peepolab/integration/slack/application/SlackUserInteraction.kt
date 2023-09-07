package pl.peepolab.integration.slack.application

import com.slack.api.bolt.socket_mode.SocketModeApp
import com.slack.api.methods.MethodsClient
import pl.peepolab.integration.slack.model.SlackUser
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.utilities.datatype.Email

interface SlackUserInteraction {

    val slackUserService: SlackUserService

    val socketModeApp: SocketModeApp

    fun retrieveSlackUser(slackUserId: SlackUserId): SlackUser {
        return slackUserService.findSlackUser(slackUserId) ?: slackUserService.createSlackUser(
            getCreateSlackUserData(slackUserId)
        )
    }

    private fun getCreateSlackUserData(slackUserId: SlackUserId): CreateSlackUserData {
        val response = socketModeApp.client.slack.methods().usersInfo { it.user(slackUserId.value) }
        val user = response.user
        return CreateSlackUserData(
            slackUserId = slackUserId,
            email = Email(user.profile.email),
            name = user.name,
            realName = user.realName,
            avatar = user.profile.imageOriginal
        )
    }

}