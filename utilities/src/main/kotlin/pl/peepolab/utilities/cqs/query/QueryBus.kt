package pl.peepolab.utilities.cqs.query

interface QueryBus {

    fun <RESULT> executeQuery(query: Query<RESULT>): RESULT

}