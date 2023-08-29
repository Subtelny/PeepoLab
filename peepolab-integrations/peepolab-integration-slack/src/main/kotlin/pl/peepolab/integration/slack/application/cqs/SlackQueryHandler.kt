package pl.peepolab.integration.slack.application.cqs

import pl.peepolab.utilities.cqs.query.Query
import pl.peepolab.utilities.cqs.query.QueryHandler

interface SlackQueryHandler<QUERY : Query<RESULT>, RESULT> : QueryHandler<QUERY, RESULT>