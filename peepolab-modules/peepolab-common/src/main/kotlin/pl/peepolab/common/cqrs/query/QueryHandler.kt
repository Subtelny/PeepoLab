package pl.peepolab.common.cqrs.query

interface QueryHandler<QUERY : Query<RESULT>, RESULT> {

    fun handle(query: QUERY): RESULT

}