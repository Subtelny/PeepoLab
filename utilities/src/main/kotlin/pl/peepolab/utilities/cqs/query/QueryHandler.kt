package pl.peepolab.utilities.cqs.query

interface QueryHandler<QUERY : Query<RESULT>, RESULT> {

    fun handle(query: QUERY): RESULT

}