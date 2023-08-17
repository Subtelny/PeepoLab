package pl.peepolab.core.infrastructure.query

import io.micronaut.context.ApplicationContext
import pl.peepolab.core.infrastructure.Provider
import java.lang.reflect.Type

class QueryProvider<HANDLER : QueryHandler<*, *>>(
    applicationContext: ApplicationContext,
    type: Type,
) : Provider<HANDLER>(applicationContext, type)