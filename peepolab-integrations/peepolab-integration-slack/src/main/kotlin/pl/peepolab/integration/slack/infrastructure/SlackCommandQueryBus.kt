package pl.peepolab.integration.slack.infrastructure

import jakarta.inject.Singleton
import pl.peepolab.integration.slack.application.cqs.SlackCommandBus
import pl.peepolab.integration.slack.application.cqs.SlackQueryBus
import pl.peepolab.utilities.cqs.EmptyHandlerRegistry
import pl.peepolab.utilities.cqs.HandlerRegistry
import pl.peepolab.utilities.cqs.command.Command
import pl.peepolab.utilities.cqs.command.CommandHandler
import pl.peepolab.utilities.cqs.query.Query
import pl.peepolab.utilities.cqs.query.QueryHandler

@Singleton
internal class SlackCommandQueryBus : SlackCommandBus, SlackQueryBus {

    private var handlerRegistry: HandlerRegistry = EmptyHandlerRegistry

    @Suppress("UNCHECKED_CAST")
    override fun executeCommand(command: Command) {
        val handler = handlerRegistry.getCommandHandler(command::class.java) as CommandHandler<Command>
        handler.handle(command)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <RESULT> executeQuery(query: Query<RESULT>): RESULT {
        val handler = handlerRegistry.getQueryHandler(query::class.java) as QueryHandler<Query<RESULT>, RESULT>
        return handler.handle(query)
    }

    fun setupHandlerRegistry(handlerRegistry: HandlerRegistry) {
        this.handlerRegistry = handlerRegistry
    }
}