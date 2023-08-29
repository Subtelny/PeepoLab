package pl.peepolab.utilities.cqs

import pl.peepolab.utilities.cqs.command.Command
import pl.peepolab.utilities.cqs.command.CommandHandler
import pl.peepolab.utilities.cqs.query.Query
import pl.peepolab.utilities.cqs.query.QueryHandler

abstract class HandlerRegistry {

    protected val commandHandlers: MutableMap<Class<*>, CommandHandler<*>> = mutableMapOf()
    protected val queryHandlers: MutableMap<Class<*>, QueryHandler<*, *>> = mutableMapOf()

    @Suppress("UNCHECKED_CAST")
    fun <COMMAND : Command> getCommandHandler(commandType: Class<COMMAND>): CommandHandler<COMMAND> {
        return commandHandlers[commandType] as? CommandHandler<COMMAND>
            ?: throw IllegalArgumentException("Handler not found for command type: ${commandType.name}")
    }

    @Suppress("UNCHECKED_CAST")
    fun <RESULT, QUERY : Query<RESULT>> getQueryHandler(queryType: Class<QUERY>): QueryHandler<QUERY, RESULT> {
        return queryHandlers[queryType] as? QueryHandler<QUERY, RESULT>
            ?: throw IllegalArgumentException("Handler not found for query type: ${queryType.name}")
    }

}