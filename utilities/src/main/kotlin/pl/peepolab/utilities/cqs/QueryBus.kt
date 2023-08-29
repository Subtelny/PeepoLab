package pl.peepolab.utilities.cqs

import pl.peepolab.utilities.cqs.query.Query

interface QueryBus {

    fun <RESULT> executeQuery(query: Query<RESULT>): RESULT

}