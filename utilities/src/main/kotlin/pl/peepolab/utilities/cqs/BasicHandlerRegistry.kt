package pl.peepolab.utilities.cqs

import pl.peepolab.utilities.cqs.command.Command
import pl.peepolab.utilities.cqs.command.CommandHandler
import pl.peepolab.utilities.cqs.query.Query
import pl.peepolab.utilities.cqs.query.QueryHandler

class BasicHandlerRegistry(
    private val commandHandlers: Map<Class<*>, CommandHandler<*, *>>,
    private val queryHandlers: Map<Class<*>, QueryHandler<*, *>>,
) : HandlerRegistry {

    @Suppress("UNCHECKED_CAST")
    override fun <RESULT, COMMAND : Command<RESULT>> findCommandHandler(commandType: Class<COMMAND>): CommandHandler<COMMAND, RESULT>? {
        return commandHandlers[commandType] as? CommandHandler<COMMAND, RESULT>
    }

    @Suppress("UNCHECKED_CAST")
    override fun <RESULT, QUERY : Query<RESULT>> findQueryHandler(queryType: Class<QUERY>): QueryHandler<QUERY, RESULT>? {
        return queryHandlers[queryType] as? QueryHandler<QUERY, RESULT>
    }

}