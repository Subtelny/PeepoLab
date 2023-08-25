package pl.peepolab.module.api.cqrs

import pl.peepolab.module.api.cqrs.query.Query

interface QueryBus {

    fun <RESULT> executeQuery(query: Query<RESULT>): RESULT

}