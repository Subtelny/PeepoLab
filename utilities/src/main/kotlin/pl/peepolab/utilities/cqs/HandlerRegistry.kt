package pl.peepolab.utilities.cqs

import pl.peepolab.utilities.cqs.command.Command
import pl.peepolab.utilities.cqs.command.CommandHandler
import pl.peepolab.utilities.cqs.query.Query
import pl.peepolab.utilities.cqs.query.QueryHandler

interface HandlerRegistry {

    fun <COMMAND : Command> findCommandHandler(commandType: Class<COMMAND>): CommandHandler<COMMAND>?

    fun <RESULT, QUERY : Query<RESULT>> findQueryHandler(queryType: Class<QUERY>): QueryHandler<QUERY, RESULT>?

    fun <COMMAND : Command> getCommandHandler(commandType: Class<COMMAND>): CommandHandler<COMMAND> {
        return findCommandHandler(commandType)
            ?: throw IllegalArgumentException("Handler not found for command type: ${commandType.name}")
    }

    fun <RESULT, QUERY : Query<RESULT>> getQueryHandler(queryType: Class<QUERY>): QueryHandler<QUERY, RESULT> {
        return findQueryHandler(queryType)
            ?: throw IllegalArgumentException("Handler not found for query type: ${queryType.name}")
    }

}