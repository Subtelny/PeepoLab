package pl.peepolab.core.infrastructure.query

interface QueryHandler<QUERY : Query<RESULT>, RESULT> {

    fun handle(query: QUERY): RESULT

}