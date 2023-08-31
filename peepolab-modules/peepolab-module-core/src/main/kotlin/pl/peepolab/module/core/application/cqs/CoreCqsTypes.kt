package pl.peepolab.module.core.application.cqs

import pl.peepolab.utilities.cqs.CommandBus
import pl.peepolab.utilities.cqs.QueryBus
import pl.peepolab.utilities.cqs.command.Command
import pl.peepolab.utilities.cqs.command.CommandHandler
import pl.peepolab.utilities.cqs.query.Query
import pl.peepolab.utilities.cqs.query.QueryHandler

interface CoreCommandBus : CommandBus

interface CoreQueryBus : QueryBus

interface CoreCommandHandler<COMMAND : Command> : CommandHandler<COMMAND>

interface CoreQueryHandler<QUERY : Query<RESULT>, RESULT> : QueryHandler<QUERY, RESULT>