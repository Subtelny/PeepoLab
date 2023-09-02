package pl.peepolab.module.core.application.cqs

import pl.peepolab.module.api.ui.command.CoreCommand
import pl.peepolab.module.api.ui.query.CoreQuery
import pl.peepolab.utilities.cqs.CommandBus
import pl.peepolab.utilities.cqs.QueryBus
import pl.peepolab.utilities.cqs.command.Command
import pl.peepolab.utilities.cqs.command.CommandHandler
import pl.peepolab.utilities.cqs.query.Query
import pl.peepolab.utilities.cqs.query.QueryHandler

interface CoreCommandBus : CommandBus

interface CoreQueryBus : QueryBus

interface CoreCommandHandler<COMMAND : CoreCommand> : CommandHandler<COMMAND>

interface CoreQueryHandler<QUERY : CoreQuery<RESULT>, RESULT> : QueryHandler<QUERY, RESULT>