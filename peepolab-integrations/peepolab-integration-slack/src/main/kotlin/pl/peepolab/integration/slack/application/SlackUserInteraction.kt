package pl.peepolab.integration.slack.application

import com.slack.api.bolt.socket_mode.SocketModeApp
import pl.peepolab.integration.slack.model.CreateSlackUserData
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
        val methods = socketModeApp.client.slack.methods(socketModeApp.app.config().singleTeamBotToken)
        val response = methods.usersInfo { it.user(slackUserId.value) }
        val user = response.user
        return CreateSlackUserData(
            slackUserId = slackUserId,
            email = Email(user.profile.email),
            name = user.name,
            realName = user.realName,
            avatar = user.profile.image32,
        )
    }

}