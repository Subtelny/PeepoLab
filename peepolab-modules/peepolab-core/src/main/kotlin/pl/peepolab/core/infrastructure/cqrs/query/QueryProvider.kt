package pl.peepolab.core.infrastructure.cqrs.query

import io.micronaut.context.ApplicationContext
import pl.peepolab.common.cqrs.query.QueryHandler
import pl.peepolab.core.infrastructure.cqrs.Provider
import java.lang.reflect.Type

class QueryProvider<HANDLER : QueryHandler<*, *>>(
    applicationContext: ApplicationContext,
    type: Type,
) : Provider<HANDLER>(applicationContext, type)