package pl.peepolab.core.infrastructure

import jakarta.inject.Singleton
import pl.peepolab.core.infrastructure.command.Command
import pl.peepolab.core.infrastructure.command.CommandHandler
import pl.peepolab.core.infrastructure.query.Query
import pl.peepolab.core.infrastructure.query.QueryHandler

@Singleton
class CommandQueryBus(
    private val handlerRegistry: HandlerRegistry,
) : Bus {

    override fun <RESULT> executeCommand(command: Command<RESULT>): RESULT {
        val handler = handlerRegistry.getCommandHandler(command::class.java) as CommandHandler<Command<RESULT>, RESULT>
        return handler.handle(command)
    }

    override fun <RESULT> executeQuery(query: Query<RESULT>): RESULT {
        val handler = handlerRegistry.getQueryHandler(query::class.java) as QueryHandler<Query<RESULT>, RESULT>
        return handler.handle(query)
    }

}