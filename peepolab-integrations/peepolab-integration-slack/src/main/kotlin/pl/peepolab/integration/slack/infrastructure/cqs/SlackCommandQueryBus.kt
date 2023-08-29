package pl.peepolab.integration.slack.infrastructure.cqs

import jakarta.inject.Singleton
import pl.peepolab.integration.slack.application.cqs.SlackCommandBus
import pl.peepolab.integration.slack.application.cqs.SlackQueryBus
import pl.peepolab.utilities.cqs.CommandQueryBus
import pl.peepolab.utilities.cqs.command.Command
import pl.peepolab.utilities.cqs.query.Query

@Singleton
class SlackCommandQueryBus(
    slackHandlerRegistry: SlackHandlerRegistry,
) : SlackCommandBus, SlackQueryBus {

    private val commandQueryBus: CommandQueryBus = CommandQueryBus(slackHandlerRegistry)
    override fun executeCommand(command: Command) = commandQueryBus.executeCommand(command)

    override fun <RESULT> executeQuery(query: Query<RESULT>): RESULT = commandQueryBus.executeQuery(query)
}