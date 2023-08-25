package pl.peepolab.module.core.infrastructure.cqrs.query

import io.micronaut.context.ApplicationContext
import pl.peepolab.module.core.infrastructure.cqrs.Provider
import java.lang.reflect.Type

class QueryProvider<HANDLER : QueryHandler<*, *>>(
    applicationContext: ApplicationContext,
    type: Type,
) : Provider<HANDLER>(applicationContext, type)