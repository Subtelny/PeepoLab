package pl.peepolab.integration.slack.application.cqs

import pl.peepolab.utilities.cqs.CommandBus
import pl.peepolab.utilities.cqs.QueryBus
import pl.peepolab.utilities.cqs.command.Command
import pl.peepolab.utilities.cqs.command.CommandHandler
import pl.peepolab.utilities.cqs.query.Query
import pl.peepolab.utilities.cqs.query.QueryHandler

interface SlackCommandBus : CommandBus
interface SlackCommandHandler<COMMAND : Command> : CommandHandler<COMMAND>
interface SlackQueryBus : QueryBus
interface SlackQueryHandler<QUERY : Query<RESULT>, RESULT> : QueryHandler<QUERY, RESULT>