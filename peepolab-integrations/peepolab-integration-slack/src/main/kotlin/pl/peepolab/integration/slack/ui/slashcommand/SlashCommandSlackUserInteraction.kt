package pl.peepolab.integration.slack.ui.slashcommand

import com.slack.api.bolt.context.builtin.SlashCommandContext
import com.slack.api.bolt.request.builtin.SlashCommandRequest
import com.slack.api.bolt.response.Response
import pl.peepolab.integration.slack.application.SlackUserInteraction
import pl.peepolab.integration.slack.model.SlackUser
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.datatype.Email
import java.util.*

abstract class SlashCommandSlackUserInteraction : SlackUserInteraction, SlackSlashCommandHandler {

    override fun apply(req: SlashCommandRequest, context: SlashCommandContext): Response {
        val rawSlackUserId = req.payload.userId
        val slackUserId = SlackUserId.of(rawSlackUserId)
        val invoker = SlackUser(CoreUserId(UUID.randomUUID()), slackUserId, Email("asd"), "asdas", "asdsad", "asda")//retrieveSlackUser(slackUserId)
        return apply(invoker, req, context)
    }

    abstract fun apply(invoker: SlackUser, req: SlashCommandRequest, context: SlashCommandContext): Response

}