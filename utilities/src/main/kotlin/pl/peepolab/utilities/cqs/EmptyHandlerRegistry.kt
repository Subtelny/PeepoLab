package pl.peepolab.utilities.cqs

import pl.peepolab.utilities.cqs.command.Command
import pl.peepolab.utilities.cqs.command.CommandHandler
import pl.peepolab.utilities.cqs.query.Query
import pl.peepolab.utilities.cqs.query.QueryHandler

object EmptyHandlerRegistry : HandlerRegistry {
    override fun <RESULT, COMMAND : Command<RESULT>> findCommandHandler(commandType: Class<COMMAND>): CommandHandler<COMMAND, RESULT>? =
        null

    override fun <RESULT, QUERY : Query<RESULT>> findQueryHandler(queryType: Class<QUERY>): QueryHandler<QUERY, RESULT>? =
        null
}