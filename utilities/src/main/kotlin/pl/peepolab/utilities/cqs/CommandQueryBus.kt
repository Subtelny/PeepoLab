package pl.peepolab.utilities.cqs

import pl.peepolab.utilities.cqs.command.Command
import pl.peepolab.utilities.cqs.command.CommandHandler
import pl.peepolab.utilities.cqs.query.Query
import pl.peepolab.utilities.cqs.query.QueryHandler

class CommandQueryBus(
    private val handlerRegistry: HandlerRegistry,
) : CommandBus, QueryBus {

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

}