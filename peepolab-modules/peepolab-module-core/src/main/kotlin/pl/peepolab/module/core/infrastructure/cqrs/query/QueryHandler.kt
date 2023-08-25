package pl.peepolab.module.core.infrastructure.cqrs.query

import pl.peepolab.module.api.cqrs.query.Query

interface QueryHandler<QUERY : Query<RESULT>, RESULT> {

    fun handle(query: QUERY): RESULT

}