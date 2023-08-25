package pl.peepolab.module.core.infrastructure.cqrs

import jakarta.inject.Singleton
import pl.peepolab.module.api.cqrs.CommandBus
import pl.peepolab.module.api.cqrs.QueryBus
import pl.peepolab.module.api.cqrs.command.Command
import pl.peepolab.module.core.infrastructure.cqrs.command.CommandHandler
import pl.peepolab.module.api.cqrs.query.Query
import pl.peepolab.module.core.infrastructure.cqrs.query.QueryHandler

@Singleton
class CommandQueryBus(
    private val handlerRegistry: HandlerRegistry,
) : CommandBus, QueryBus {

    @Suppress("UNCHECKED_CAST")
    override fun <RESULT> executeCommand(command: Command<RESULT>): RESULT {
        val handler = handlerRegistry.getCommandHandler(command::class.java) as CommandHandler<Command<RESULT>, RESULT>
        return handler.handle(command)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <RESULT> executeQuery(query: Query<RESULT>): RESULT {
        val handler = handlerRegistry.getQueryHandler(query::class.java) as QueryHandler<Query<RESULT>, RESULT>
        return handler.handle(query)
    }

}