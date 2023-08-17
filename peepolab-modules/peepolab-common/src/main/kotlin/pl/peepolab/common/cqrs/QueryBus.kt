package pl.peepolab.common.cqrs

import pl.peepolab.common.cqrs.query.Query

interface QueryBus {

    fun <RESULT> executeQuery(query: Query<RESULT>): RESULT

}